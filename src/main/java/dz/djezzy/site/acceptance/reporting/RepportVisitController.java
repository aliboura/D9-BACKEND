package dz.djezzy.site.acceptance.reporting;

import com.google.zxing.WriterException;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/export-visit")
public class RepportVisitController {

    private final ReportService reportService;
    private final AuditSiteService auditSiteService;

    @PostMapping(value = "/toPDf", params = {"sort", "field", "search", "engineer", "statusId", "fromDate", "toDate"})
    public ResponseEntity<byte[]> exportToPdf(@RequestBody ReportDto report,
                                              @RequestParam("sort") String sort,
                                              @RequestParam("field") String field,
                                              @RequestParam("engineer") String engineer,
                                              @RequestParam("statusId") Integer status,
                                              @RequestParam("fromDate") String fromDate,
                                              @RequestParam("toDate") String toDate)
            throws IOException, JRException, WriterException, ParseException {

        Map<String, Object> params = new HashMap<>();
        JRBeanCollectionDataSource dataSourceLines;
        if (fromDate != null) {
            dataSourceLines = new JRBeanCollectionDataSource(auditSiteService.findByEngineerSite(engineer, status, AppsUtils.getDate(fromDate), AppsUtils.getDate(toDate)));
        } else {
            dataSourceLines = new JRBeanCollectionDataSource(auditSiteService.findByEngineerSite(engineer, status));
        }
        params.put("P_GROUP_BY", report.getGroupBy());
        params.put("PAuditSiteCollect", dataSourceLines);
        params.put("P_QR", AppsUtils.geQrCode(report));
        params.put("P_USER_PRINT", AppsUtils.getUserPrincipal().toUpperCase());

        JasperPrint jasperPrint = reportService.coompileReport("audit", Arrays.asList(report), params);
        return reportService.exportToPDF(jasperPrint, "application/pdf", report.getTitle());
    }


    @PostMapping(value = "/toExcel", params = {"sort", "field", "search", "engineer", "statusId", "fromDate", "toDate"})
    public void exportToExcel(@RequestBody ReportDto report,
                              HttpServletResponse response,
                              @RequestParam("sort") String sort,
                              @RequestParam("field") String field,
                              @RequestParam("engineer") String engineer,
                              @RequestParam("statusId") Integer status,
                              @RequestParam("fromDate") String fromDate,
                              @RequestParam("toDate") String toDate)
            throws IOException, JRException, ParseException {

        Map<String, Object> params = new HashMap<>();
        JRBeanCollectionDataSource dataSourceLines;
        if (fromDate != null) {
            dataSourceLines = new JRBeanCollectionDataSource(auditSiteService.findByEngineerSite(engineer, status, AppsUtils.getDate(fromDate), AppsUtils.getDate(toDate)));
        } else {
            dataSourceLines = new JRBeanCollectionDataSource(auditSiteService.findByEngineerSite(engineer, status));
        }
        params.put("P_GROUP_BY", report.getGroupBy());
        params.put("P_USER_PRINT", AppsUtils.getUserPrincipal().toUpperCase());
        params.put("PAuditSiteCollect", dataSourceLines);

        JasperPrint jasperPrint = reportService.coompileReport("audit", Arrays.asList(report), params);
        reportService.exportToExcel(response, jasperPrint, "application/x-xls", report.getTitle());
    }
}
