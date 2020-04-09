package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
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


}
