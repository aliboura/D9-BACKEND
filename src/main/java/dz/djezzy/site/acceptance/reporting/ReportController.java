package dz.djezzy.site.acceptance.reporting;

import com.google.zxing.WriterException;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.specification.CustomRsqlVisitor;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/export")
public class ReportController {

    private final ReportService reportService;
    private final AuditSiteService auditSiteService;


    @PostMapping(value = "/toPDf", params = {"sort", "field", "search"})
    public ResponseEntity<byte[]> exportToPdf(@RequestBody ReportDto report,
                                              @RequestParam("sort") String sort,
                                              @RequestParam("field") String field,
                                              @RequestParam("search") String search)
            throws IOException, JRException, WriterException {

        Node rootNode = new RSQLParser().parse(search);
        Specification<AuditSite> spec = rootNode.accept(new CustomRsqlVisitor<>());

        Map<String, Object> params = new HashMap<>();
        JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSiteService.findAll(spec, Sort.by(AppsUtils.getSortDirection(sort), field)));
        params.put("P_GROUP_BY", report.getGroupBy());
        params.put("PAuditSiteCollect", dataSourceLines);
        params.put("P_QR", AppsUtils.geQrCode(report));

        JasperPrint jasperPrint = reportService.coompileReport("audit", Arrays.asList(report), params);
        return reportService.exportToPDF(jasperPrint, "application/pdf", report.getTitle());
    }


    @PostMapping(value = "/toExcel", params = {"sort", "field", "search"})
    public void exportToExcel(@RequestBody ReportDto report,
                              HttpServletResponse response,
                              @RequestParam("sort") String sort,
                              @RequestParam("field") String field,
                              @RequestParam("search") String search)
            throws IOException, JRException {
        Node rootNode = new RSQLParser().parse(search);
        Specification<AuditSite> spec = rootNode.accept(new CustomRsqlVisitor<>());

        Map<String, Object> params = new HashMap<>();
        JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSiteService.findAll(spec, Sort.by(AppsUtils.getSortDirection(sort), field)));
        params.put("P_GROUP_BY", report.getGroupBy());
        params.put("PAuditSiteCollect", dataSourceLines);

        JasperPrint jasperPrint = reportService.coompileReport("audit", Arrays.asList(report), params);
        reportService.exportToExcel(response, jasperPrint, "application/x-xls", report.getTitle());
    }

}
