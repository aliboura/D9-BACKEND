package dz.djezzy.site.acceptance.business.data.dto;

import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import dz.djezzy.site.acceptance.business.data.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditSiteLineDto extends AuditableDto {


    private Integer id;
    private String label;
    private Integer auditSiteId;

    private Integer subCategoriesId;

    private Integer valueType;
    private boolean blocking;

    private Integer categoriesId;

    private String categoriesLabel;

    private String observation;

    private byte[] image;

    private Integer firstDecisionId;
    private String firstDecisionLabel;

    private Integer secondDecisionId;
    private String secondDecisionLabel;

    public AuditSiteLineDto(AuditSiteLineCSV auditSiteLineCSV, Integer auditSiteId) {
        this.auditSiteId = auditSiteId;
        this.label = auditSiteLineCSV.getLabel();
        this.categoriesId = auditSiteLineCSV.getCategoriesId();
        this.categoriesLabel = auditSiteLineCSV.getCategoriesLabel();
        this.subCategoriesId = auditSiteLineCSV.getSubCategoriesId();
        this.observation = auditSiteLineCSV.getObservation();
        this.firstDecisionLabel = !auditSiteLineCSV.getFirstDecisionLabel().equals("") ? auditSiteLineCSV.getFirstDecisionLabel() : StatusEnum.None.toString();
        this.firstDecisionId = getFirstDecision(auditSiteLineCSV.getFirstDecisionLabel());
        this.blocking = auditSiteLineCSV.getFirstDecisionLabel().contains("RB");
        this.valueType = 1;
    }

    public Integer getFirstDecision(String firstDecisionLabel) {
        if (firstDecisionLabel.equals(StatusEnum.Conform.toString())) {
            return 2;
        } else if (firstDecisionLabel.equals(StatusEnum.No_Conform.toString())) {
            return 3;
        }
        return 1;
    }

}
