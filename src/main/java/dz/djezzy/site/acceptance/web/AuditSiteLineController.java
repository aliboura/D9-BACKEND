package dz.djezzy.site.acceptance.web;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import dz.djezzy.site.acceptance.business.data.dto.*;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.business.services.AuditSiteLineService;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.business.services.CategoriesService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import lombok.AllArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.AUDIT_SITE_LINE_API)
public class AuditSiteLineController extends GenericRestController<AuditSiteLineService, AuditSiteLine, AuditSiteLineDto, Integer> {

    private final AuditSiteService auditSiteService;
    private final CategoriesService categoriesService;

    @PutMapping(value = "/goToNext", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AuditSiteDto goToNextSteps(@RequestBody AuditStepsDto steps) {
        return auditSiteService.goToNextSteps(steps);
    }

    @PostMapping(value = "/saveFiles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AuditSiteDto> saveFiles(Model model, @RequestBody AuditSiteDto auditSiteDto) throws MalformedURLException {
        Path pathFile = Paths.get(".\\uploads\\forms-d9.csv");
        UrlResource resource = new UrlResource(pathFile.toUri());
        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

            // create csv bean reader
            CsvToBean<Object> csvToBean = new CsvToBeanBuilder<>(reader)
                    .withType(AuditSiteLineCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of auditLines
            List<Object> auditSiteLines = csvToBean.parse();

            CategoriesDto lastCategory = categoriesService.findLastCategory();
            auditSiteDto.setCurrentCategoriesId(lastCategory.getId());
            auditSiteDto.setCurrentCategoriesLabel(lastCategory.getLabel());

            auditSiteService.saveAllCsvLines(auditSiteLines, auditSiteDto);

            // save auditLines list on model
            model.addAttribute("auditSiteLines", auditSiteLines);
            model.addAttribute("status", true);
            return ResponseEntity.status(HttpStatus.OK).body(auditSiteDto);

        } catch (Exception ex) {
            model.addAttribute("message", "An error occurred while processing the CSV file.");
            model.addAttribute("status", false);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

}
