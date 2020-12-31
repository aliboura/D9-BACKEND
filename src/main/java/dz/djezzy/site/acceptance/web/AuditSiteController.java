package dz.djezzy.site.acceptance.web;

import com.google.zxing.WriterException;
import com.sun.mail.util.MailConnectException;
import dz.djezzy.site.acceptance.business.data.dto.*;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.WilayaRegion;
import dz.djezzy.site.acceptance.business.data.enums.StatusEnum;
import dz.djezzy.site.acceptance.business.services.*;
import dz.djezzy.site.acceptance.reporting.ReportService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.AUDIT_SITE_API)
public class AuditSiteController extends GenericRestController<AuditSiteService, AuditSite, AuditSiteDto, Integer> {

    private final SiteService siteService;
    private final UserService userService;
    private final MailService mailService;
    private final ReportService reportService;
    private final AuditSiteService auditSiteService;
    private final CategoriesService categoriesService;
    private final WilayaRegionService wilayaRegionService;
    private final AuditSiteLineService auditSiteLineService;
    private final StatusAuditSiteService statusAuditSiteService;

    @Transactional
    @Override
    @PostMapping
    public AuditSiteDto create(@RequestBody AuditSiteDto entity) {
        AuditSiteDto saved = super.create(entity);
        if (saved.getAudited()) {
            Optional<SiteDto> siteOpt = siteService.findById(entity.getSiteId());
            if (siteOpt.isPresent() && !siteOpt.get().getAudited()) {
                SiteDto site = siteOpt.get();
                site.setAudited(true);
                siteService.save(site);
            }
        }
        StatusAuditSiteDto dto = auditSiteService.createStatusAudit(saved);
        statusAuditSiteService.save(dto);
        return saved;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ENGINEER_SITE','ROLE_ENGINEER_OM')")
    @Transactional
    @PutMapping(value = "/goToFinish")
    public AuditSiteDto goToFinish(@RequestBody AuditStepsDto steps) throws WriterException, JRException {
        AuditSiteDto doSaved = auditSiteService.goToNextSteps(steps);
        Optional<StatusDto> status = auditSiteService.checkStatus(doSaved);
        if (status.isPresent()) {
            doSaved.setCurrentSatusId(status.get().getId());
            doSaved.setCurrentSatusLabel(status.get().getLabel());
            if (status.get().getLabel().equals(StatusEnum.Conform.toString())) {
                doSaved.setClosed(true);
                Optional<SiteDto> site = siteService.findById(doSaved.getSiteId());
                if (site.isPresent()) {
                    site.get().setClosed(true);
                    siteService.save(site.get());
                }
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

        if (status.isPresent() && isLast(status.get())) {
            sendMail(setUsers(saved, steps.getAuditSite()));
        }
        return saved;
    }

    private AuditSiteDto setUsers(AuditSiteDto output, AuditSiteDto input) {
        if (input.getSiteUserV1() != null && !input.getSiteUserV1().equals("-")) {
            output.setSiteUserV1(input.getSiteUserV1());
        }
        if (input.getSiteUserV2() != null && !input.getSiteUserV2().equals("-")) {
            output.setSiteUserV2(input.getSiteUserV2());
        }
        if (input.getSiteUserOMV1() != null && !input.getSiteUserOMV1().equals("-")) {
            output.setSiteUserOMV1(input.getSiteUserOMV1());
        }
        if (input.getSiteUserOMV2() != null && !input.getSiteUserOMV2().equals("-")) {
            output.setSiteUserOMV2(input.getSiteUserOMV2());
        }
        return output;
    }

    private boolean isLast(StatusDto status) {
        return status.getLabel().equalsIgnoreCase(StatusEnum.No_Conform.toString()) || status.getLabel().equalsIgnoreCase(StatusEnum.Conform.toString()) || status.getLabel().equalsIgnoreCase(StatusEnum.Accepted.toString());
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
    public ResponseEntity<AuditSiteDto> secondVisit(@RequestBody AuditSecondVisitDto entity) {
        if (!entity.getAuditSiteLines().isEmpty()) {
            auditSiteLineService.saveAll(entity.getAuditSiteLines());
        }
        AuditSiteDto doSave = auditSiteService.setCurrentStatus(entity.getAuditSite(), StatusEnum.In_Progress_Validate_V2.toString());
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

    private List<AuditRecap> getAuditRecap(AuditSiteDto auditSiteDto) {
        List<AuditRecap> recaps = new ArrayList<>();
        List<CategoriesDto> categoriesList = categoriesService.findByStatus(Boolean.TRUE).stream()
                .sorted(Comparator.comparingInt(CategoriesDto::getOrderNum))
                .collect(Collectors.toList());
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

    @GetMapping(params = {"page", "size", "sort", "field", "engineer"})
    public Page<AuditSiteDto> findByEngineerSite(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("engineer") String username) {
        return auditSiteService.findByEngineerSite(username, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    private JasperPrint getJasperPrint(AuditSiteDto auditSite) throws IOException, JRException, WriterException {
        List<AuditSiteDto> data = Arrays.asList(auditSite);
        Map<String, Object> params = new HashMap<>();
        params.put("P_AUDIT", auditSite.getId());
        JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSite.getAuditSiteLineDtoList());
        params.put("PAuditSiteLinesCollect", dataSourceLines);
        List<String> status = auditSite.getStatusAuditSitesDtoList().stream().map(x -> x.getStatusLabel() + " - " + x.getStatusDate()).collect(Collectors.toList());
        InputStream qrcode = AppsUtils.writeImage("png", AppsUtils.generateMatrix(status.toString(), 400));
        params.put("P_QR", qrcode);
        params.put("P_USER_PRINT", AppsUtils.getUserPrincipal().toUpperCase());
        JRBeanCollectionDataSource dataSourceRecaps = new JRBeanCollectionDataSource(getAuditRecap(auditSite));
        params.put("PAuditRecapsCollect", dataSourceRecaps);

        return reportService.coompileReport("d9-forms", data, params);
    }

    @GetMapping(value = "/toPdf", params = {"id"})
    public ResponseEntity<byte[]> exportToPdf(@RequestParam("id") Integer id) throws IOException, JRException, WriterException {
        Optional<AuditSiteDto> auditSite = auditSiteService.findById(id);
        if (auditSite.isPresent()) {
            JasperPrint jasperPrint = getJasperPrint(auditSite.get());
            return reportService.exportToPDF(jasperPrint, "application/pdf", "audit-d9");
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
            params.put("P_USER_PRINT", AppsUtils.getUserPrincipal().toUpperCase());

            JasperPrint jasperPrint = reportService.coompileReport("d9-forms", data, params);

            reportService.exportToExcel(response, jasperPrint, "application/x-xls", "filename=d9-forms");
        }
    }


    private MailResponse<String> sendMail(AuditSiteDto entity) throws JRException, WriterException {
        try {
            List<String> emails = new ArrayList<>();
            List<String> cc = new ArrayList<>();
            JasperPrint jasperPrint = getJasperPrint(entity);
            DataSource aAttachment = new ByteArrayDataSource(reportService.exportToByte(jasperPrint), "application/pdf");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            List<UserInfo> list = userService.findUserInfo(Arrays.asList(
                    entity.getSecondVisit() ? entity.getSiteUserV2() : entity.getSiteUserV1(),
                    entity.getSecondVisit() ? entity.getSiteUserOMV2() : entity.getSiteUserOMV2()));
            emails.addAll(list.stream().map(x -> x.getEmail()).collect(Collectors.toList()));
            Optional<WilayaRegionDto> wilaya = wilayaRegionService.findById(entity.getWilayaId());
            if (wilaya.isPresent()) {
                if (wilaya.get().getGroupSites() != null)
                    cc.addAll(wilaya.get().getGroupSites());
                if (wilaya.get().getGroupOMs() != null)
                    cc.addAll(wilaya.get().getGroupOMs());
            }
            MailResponse<String> send = mailService.sendNotifications(
                    false,
                    new MailRequest(entity.getSiteCode(),
                            dateFormat.format(entity.getSecondVisit() ? entity.getSecondDecisionDate() : entity.getFirstDecisionDate()),
                            entity.getSiteName(),
                            entity.getSecondVisit() ? entity.getSecondDecisionEngineerSite() : entity.getFirstDecisionEngineerSite(),
                            entity.getSecondVisit() ? entity.getSecondDecisionEngineerOM() : entity.getFirstDecisionEngineerOM(),
                            entity.getSecondVisit() ? entity.getSecondDecisionLabel() : entity.getFirstDecisionLabel(),
                            emails.toArray(new String[0]),
                            cc.toArray(new String[0])),
                    aAttachment);
            if (!send.isSuccess()) {
                return new MailResponse<>("Erreur dans l'envoie du mail.");
            }
            return new MailResponse<>("OK");
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (IOException e) {
            return new MailResponse<>(e.getMessage());
        }

    }

    private String[] setMails(String mailEngineerSite, String mailEngineerOM) {
        List<String> mails = new ArrayList<>();
        if (mailEngineerSite != null && !mails.contains(mailEngineerSite)) {
            mails.add(mailEngineerSite + "@djezzy.dz");
        }
        if (mailEngineerOM != null && !mails.contains(mailEngineerSite)) {
            mails.add(mailEngineerOM + "@djezzy.dz");
        }
        return mails.toArray(new String[0]);
    }
}
