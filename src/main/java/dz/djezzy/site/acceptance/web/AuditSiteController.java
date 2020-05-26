package dz.djezzy.site.acceptance.web;

import com.google.zxing.WriterException;
import dz.djezzy.site.acceptance.business.data.dto.*;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.enums.StatusEnum;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.business.services.CategoriesService;
import dz.djezzy.site.acceptance.business.services.StatusAuditSiteService;
import dz.djezzy.site.acceptance.reporting.ReportService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.AUDIT_SITE_API)
public class AuditSiteController extends GenericRestController<AuditSiteService, AuditSite, AuditSiteDto, Integer> {

    private final StatusAuditSiteService statusAuditSiteService;
    private final AuditSiteService auditSiteService;
    private final ReportService reportService;
    private final CategoriesService categoriesService;


    @Transactional
    @Override
    @PostMapping
    public AuditSiteDto create(@RequestBody AuditSiteDto entity) {
        AuditSiteDto saved = super.create(entity);
        StatusAuditSiteDto dto = auditSiteService.createStatusAudit(saved);
        statusAuditSiteService.save(dto);
        return saved;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ENGINEER_SITE','ROLE_ENGINEER_OM')")
    @Transactional
    @PutMapping(value = "/goToFinish")
    public AuditSiteDto goToFinish(@RequestBody AuditStepsDto steps) {
        AuditSiteDto doSaved = auditSiteService.goToNextSteps(steps);
        Optional<StatusDto> status = auditSiteService.checkStatus(doSaved);
        if (status.isPresent()) {
            doSaved.setCurrentSatusId(status.get().getId());
            doSaved.setCurrentSatusLabel(status.get().getLabel());
            if (status.get().getLabel().equals(StatusEnum.Conform.toString())) {
                doSaved.setClosed(true);
            }
            if (status.get().getLabel().equals(StatusEnum.Validate_Site.toString())) {
                if (doSaved.getFirstVisit() && !doSaved.getSecondVisit()) {
                    doSaved.setFirstVisit(false);
                } else if (doSaved.getFirstVisit() && doSaved.getSecondVisit()) {
                    doSaved.setSecondVisit(false);
                }
            }
        }

        AuditSiteDto saved = auditSiteService.save(doSaved);
        if (checkExistStatus(saved)) {
            changeCurrentStatus(doSaved);
            StatusAuditSiteDto statusAuditSiteDto = auditSiteService.createStatusAudit(saved);
            statusAuditSiteService.save(statusAuditSiteDto);
        }
        return saved;
    }

    private void changeCurrentStatus(AuditSiteDto siteDto) {
        if (!siteDto.getStatusAuditSitesDtoList().isEmpty()) {
            siteDto.getStatusAuditSitesDtoList().forEach(x -> {
                x.setCurrent(false);
                x.setLast(false);
                statusAuditSiteService.save(x);
            });
        }
    }

    private boolean checkExistStatus(AuditSiteDto siteDto) {
        StatusAuditSiteDto state = siteDto.getStatusAuditSitesDtoList().stream()
                .filter(x -> x.getStatusId() == siteDto.getCurrentSatusId() && x.isCurrent())
                .findAny()
                .orElse(null);
        return state == null;
    }

    @Transactional
    @PutMapping("/second-visit")
    public ResponseEntity<AuditSiteDto> secondVisit(@RequestBody AuditSiteDto entity) {
        AuditSiteDto doSave = auditSiteService.setCurrentStatus(entity, StatusEnum.In_Progress_Validate_V2.toString());
        doSave.setSecondCheckDate(new Date());
        doSave.setSecondCheck(true);
        AuditSiteDto saved = auditSiteService.save(doSave);
        if (checkExistStatus(saved)) {
            changeCurrentStatus(saved);
            StatusAuditSiteDto dto = auditSiteService.createStatusAudit(saved);
            statusAuditSiteService.save(dto);
        }
        return ResponseEntity.ok(saved);
    }

    @GetMapping(value = "/toPdf", params = {"id"})
    public ResponseEntity<byte[]> exportToPdf(@RequestParam("id") Integer id) throws IOException, JRException, WriterException {
        Optional<AuditSiteDto> auditSite = auditSiteService.findById(id);
        if (auditSite.isPresent()) {
            List<AuditSiteDto> data = Arrays.asList(auditSite.get());
            Map<String, Object> params = new HashMap<>();
            params.put("P_AUDIT", auditSite.get().getId());
            JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSite.get().getAuditSiteLineDtoList());
            params.put("PAuditSiteLinesCollect", dataSourceLines);
            List<String> status = auditSite.get().getStatusAuditSitesDtoList().stream().map(x -> x.getStatusLabel() + " - " + x.getStatusDate()).collect(Collectors.toList());
            InputStream qrcode = AppsUtils.writeImage("png", AppsUtils.generateMatrix(status.toString(), 400));
            params.put("P_QR", qrcode);
            JRBeanCollectionDataSource dataSourceRecaps = new JRBeanCollectionDataSource(getAuditRecap(auditSite.get()));
            params.put("PAuditRecapsCollect", dataSourceRecaps);

            JasperPrint jasperPrint = reportService.exportReport("d9-forms", data, params);
            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                    .filename("audit-d9" + ".pdf").build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(contentDisposition);
            return ResponseEntity
                    .ok()
                    .header("Content-Type", "application/pdf; charset=UTF-8")
                    .headers(headers)
                    .body(bytes);
        }
        return (ResponseEntity<byte[]>) ResponseEntity.badRequest();
    }

    @GetMapping(value = "/toExcel", params = {"id"})
    public @ResponseBody
    void exportToExcel(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException, JRException {
        Optional<AuditSiteDto> auditSite = auditSiteService.findById(id);
        if (auditSite.isPresent()) {
            List<AuditSiteDto> data = Arrays.asList(auditSite.get());
            Map<String, Object> params = new HashMap<>();
            params.put("P_AUDIT", auditSite.get().getId());
            JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSite.get().getAuditSiteLineDtoList());
            params.put("PAuditSiteLinesCollect", dataSourceLines);

            JRBeanCollectionDataSource dataSourceRecaps = new JRBeanCollectionDataSource(getAuditRecap(auditSite.get()));
            params.put("PAuditRecapsCollect", dataSourceRecaps);

            JasperPrint jasperPrint = reportService.exportReport("d9-forms", data, params);
            response.setContentType("application/x-xls");
            response.setHeader("Content-Disposition", "inline; filename=d9-forms.xls");

            final OutputStream outputStream = response.getOutputStream();

            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
            xlsReportConfiguration.setOnePagePerSheet(false);
            xlsReportConfiguration.setCollapseRowSpan(true);
            xlsReportConfiguration.setIgnoreGraphics(false);
//            xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
//            xlsReportConfiguration.setDetectCellType(false);
//            xlsReportConfiguration.setWhitePageBackground(false);
            exporter.setConfiguration(xlsReportConfiguration);
            exporter.exportReport();
        }
    }


    private List<AuditRecap> getAuditRecap(AuditSiteDto auditSiteDto) {
        List<AuditRecap> recaps = new ArrayList<>();
        List<CategoriesDto> categoriesList = categoriesService.findByStatus(Boolean.TRUE);
        if (!categoriesList.isEmpty())
            for (CategoriesDto cat : categoriesList) {
                recaps.add(new AuditRecap(cat.getLabel(), checkDecision(cat, auditSiteDto)));
            }
        return recaps;
    }

    private String checkDecision(CategoriesDto categoriesDto, AuditSiteDto auditSiteDto) {
        List<AuditSiteLineDto> lines = auditSiteDto.getAuditSiteLineDtoList().stream().filter(x -> x.getCategoriesId() == categoriesDto.getId()).collect(Collectors.toList());
        String status = "-";


        List<AuditSiteLineDto> linesNoConform = checkNoConform(lines);
        if (linesNoConform.size() > 0) {
            status = StatusEnum.No_Conform.toString();
            if (!auditSiteDto.getSecondVisit()) {
                return status;
            }
        }

        List<AuditSiteLineDto> linesAccepted = checkAccepted(lines);
        if (linesAccepted.size() > 0) {
            status = StatusEnum.Accepted.toString();
            if (!auditSiteDto.getSecondVisit()) {
                return status;
            }
        }

        List<AuditSiteLineDto> linesNA = checkNone(lines);
        if (linesNA.size() > 0) {
            status = StatusEnum.None.toString();
            if (!auditSiteDto.getSecondVisit()) {
                return status;
            }
        }

        List<AuditSiteLineDto> linesConform = checkConform(lines);
        if (linesConform.size() > 0) {
            status = StatusEnum.Conform.toString();
            return status;
        }

        return status;
    }

    private List<AuditSiteLineDto> checkNoConform(List<AuditSiteLineDto> lines) {
        return lines.stream().filter(x ->
                (x.isBlocking() && StringUtils.isNotBlank(x.getFirstDecisionLabel()) &&
                        x.getFirstDecisionLabel().equals(StatusEnum.No_Conform.toString()))
                        ||
                        (x.isBlocking() && StringUtils.isNotBlank(x.getSecondDecisionLabel()) &&
                                x.getSecondDecisionLabel().equals(StatusEnum.No_Conform.toString())))
                .collect(Collectors.toList());
    }

    private List<AuditSiteLineDto> checkAccepted(List<AuditSiteLineDto> lines) {
        return lines.stream().filter(x ->
                (!x.isBlocking() && StringUtils.isNotBlank(x.getFirstDecisionLabel()) &&
                        x.getFirstDecisionLabel().equals(StatusEnum.No_Conform.toString()))
                        ||
                        (!x.isBlocking() && StringUtils.isNotBlank(x.getSecondDecisionLabel()) &&
                                x.getSecondDecisionLabel().equals(StatusEnum.No_Conform.toString())))
                .collect(Collectors.toList());
    }

    private List<AuditSiteLineDto> checkConform(List<AuditSiteLineDto> lines) {
        return lines.stream().filter(x ->
                (StringUtils.isNotBlank(x.getFirstDecisionLabel()) &&
                        x.getFirstDecisionLabel().equals(StatusEnum.Conform.toString()))
                        ||
                        (StringUtils.isNotBlank(x.getSecondDecisionLabel()) &&
                                x.getSecondDecisionLabel().equals(StatusEnum.Conform.toString())))
                .collect(Collectors.toList());
    }

    private List<AuditSiteLineDto> checkNone(List<AuditSiteLineDto> lines) {
        return lines.stream().filter(x ->
                (StringUtils.isNotBlank(x.getFirstDecisionLabel()) &&
                        x.getFirstDecisionLabel().equals(StatusEnum.None.toString()))
                        ||
                        (StringUtils.isNotBlank(x.getSecondDecisionLabel()) &&
                                x.getSecondDecisionLabel().equals(StatusEnum.None.toString())))
                .collect(Collectors.toList());
    }

}
