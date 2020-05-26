package dz.djezzy.site.acceptance.reporting;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/export")
public class ReportController {

    private final ReportService reportService;
    private final AuditSiteService auditSiteService;

    @GetMapping(value = "/pdf", params = {"id"})
    public ResponseEntity<byte[]> exportToPdf(@RequestParam("id") Integer id) throws FileNotFoundException, JRException {
        if (id != null) {
            Optional<AuditSiteDto> auditSite = auditSiteService.findById(id);
            if (auditSite.isPresent()) {
                List<AuditSiteDto> data = Arrays.asList(auditSite.get());
                Map<String, Object> params = new HashMap<>();
                params.put("P_AUDIT", auditSite.get().getId());
                JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSite.get().getAuditSiteLineDtoList());
                params.put("PAuditSiteLinesCollect", dataSourceLines);

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
        }
        return (ResponseEntity<byte[]>) ResponseEntity.badRequest();
    }

    @GetMapping(value = "/excel", params = {"id"})
    public @ResponseBody
    void exportToExcel(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException, JRException {
        if (id != null) {
            Optional<AuditSiteDto> auditSite = auditSiteService.findById(id);
            if (auditSite.isPresent()) {
                List<AuditSiteDto> data = Arrays.asList(auditSite.get());
                Map<String, Object> params = new HashMap<>();
                params.put("P_AUDIT", auditSite.get().getId());
                JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSite.get().getAuditSiteLineDtoList());
                params.put("PAuditSiteLinesCollect", dataSourceLines);

                JasperPrint jasperPrint = reportService.exportReport("d9-forms", data, params);
                response.setContentType("application/x-xls");
                response.setHeader("Content-Disposition", "inline; filename=d9-forms.xls");

                final OutputStream outputStream = response.getOutputStream();

                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
                xlsReportConfiguration.setOnePagePerSheet(false);
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
                xlsReportConfiguration.setDetectCellType(false);
                xlsReportConfiguration.setWhitePageBackground(false);
                exporter.setConfiguration(xlsReportConfiguration);
                exporter.exportReport();
            }
        }
    }

}
