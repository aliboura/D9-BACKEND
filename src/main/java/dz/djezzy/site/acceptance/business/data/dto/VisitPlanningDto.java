package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VisitPlanningDto extends AuditableDto {

    private Long id;
    private Long siteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteCode;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dateD1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String regionId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String wilayaLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean audited;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean closed;;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String typeSiteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean firstVisit;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean secondVisit;

    private String engineerSiteV1;
    private Date engineerSiteDateV1;
    private String engineerOMV1;
    private Date engineerOMDateV1;
    private String engineerSiteV2;
    private Date engineerSiteDateV2;
    private String engineerOMV2;
    private Date engineerOMDateV2;
}
