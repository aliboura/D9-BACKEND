package dz.djezzy.site.acceptance.business.data.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AuditSiteLineCSV implements Serializable {

    @CsvBindByName(column = "Description")
    private String label;

    private Integer auditSiteId;
    private Boolean blocking;

    @CsvBindByName(column = "SUB")
    private Integer subCategoriesId;

    @CsvBindByName(column = "CAT")
    private Integer categoriesId;

    @CsvBindByName(column = "Categorie")
    private String categoriesLabel;

    @CsvBindByName(column = "Observation")
    private String observation;

    private Integer firstDecisionId;

    @CsvBindByName(column = "Remarque")
    private String firstDecisionLabel;
}
