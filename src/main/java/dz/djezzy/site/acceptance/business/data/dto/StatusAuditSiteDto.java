package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private Integer id;
    private Date statusDate;
    private String description;
    private boolean current;
    private boolean last;
    private String username;
    private Integer statusId;
    private String statusLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String statusDescription;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String statusStyleCSS;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String statusIconCSS;
    private Integer auditSiteId;
}
