package dz.djezzy.site.acceptance.business.data.dto;

import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusAuditSiteDto extends AuditableDto {

    private int id;
    private Date statusDate;
    private String description;
    private boolean current;
    private int statusId;
    private String statusLabel;
    private int auditSiteId;
}
