package dz.djezzy.site.acceptance.business.data.dto;

import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
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
    private Integer categoriesId;
    private String categoriesLabel;
    private String observation;
    private byte[] image;
    private Integer firstDecisionId;
    private String firstDecisionLabel;
    private Integer secondDecisionId;
    private String secondDecisionLabel;
}
