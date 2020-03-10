package dz.djezzy.site.acceptance.business.data.dto;

import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditSiteLineDto extends AuditableDto {

    private int id;
    private int auditSiteId;
    private String observation;
    private byte[] image;
    private int firstDecisionId;
    private String firstDecisionLabel;
    private int secondDecisionId;
    private String secondDecisionLabel;
}
