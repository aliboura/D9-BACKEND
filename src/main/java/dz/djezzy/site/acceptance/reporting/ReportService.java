package dz.djezzy.site.acceptance.reporting;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@AllArgsConstructor
@Component
public class ReportService {

    private final AuditSiteService auditSiteService;

    public byte[] exportReport(Integer id) throws FileNotFoundException, JRException {
        byte[] bytes = null;
        Optional<AuditSiteDto> auditSite = auditSiteService.findById(id);
        if (auditSite.isPresent()) {
            List<AuditSiteDto> list = Arrays.asList(auditSite.get());

            File file = ResourceUtils.getFile("src/main/resources/reports/d9-forms.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("P_AUDIT", auditSite.get().getId());
            JRBeanCollectionDataSource dataSourceLines = new JRBeanCollectionDataSource(auditSite.get().getAuditSiteLineDtoList());
            parameters.put("PAuditSiteLinesCollect", dataSourceLines);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return bytes;
    }

    public <T> JasperPrint exportReport(String name, List<T> data, Map<String, Object> params) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("src/main/resources/reports/" + name + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        return jasperPrint;
    }

}
