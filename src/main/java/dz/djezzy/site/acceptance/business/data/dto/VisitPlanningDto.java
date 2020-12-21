package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VisitPlanningDto extends AuditableDto {

    private Long id;
    private Long siteId;
    private String siteCode;
    private String siteName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dateD1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String regionId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String wilayaLabel;
    private Boolean audited;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean closed;
    ;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String typeSiteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean firstVisit;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean secondVisit;

    private String engineerSiteV1;
    @Transient
    private String engineerSiteV1FullName;
    @Transient
    private String engineerSiteV1Mail;
    private Date engineerSiteDateV1;
    private String engineerOMV1;
    @Transient
    private String engineerOMV1FullName;
    @Transient
    private String engineerOMV1Mail;
    private Date engineerOMDateV1;
    private String engineerSiteV2;
    @Transient
    private String engineerSiteV2FullName;
    @Transient
    private String engineerSiteV2Mail;
    private Date engineerSiteDateV2;
    private String engineerOMV2;
    @Transient
    private String engineerOMV2FullName;
    @Transient
    private String engineerOMV2Mail;
    private Date engineerOMDateV2;
}
