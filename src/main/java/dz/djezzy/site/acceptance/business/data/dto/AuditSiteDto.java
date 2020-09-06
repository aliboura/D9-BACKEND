package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class AuditSiteDto extends AuditableDto {

    private Integer id;
    private Date auditDate;
    private String userId;
    private Integer wilayaId;
    private String regionId;
    private String repOwner;
    private String description;
    private String observation;
    private Boolean lastStep;
    private Boolean closed;
    private Boolean secondCheck;
    private Date firstCheckDate;
    private Date secondCheckDate;

    private Long siteId;
    private Boolean audited;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteCode;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dateD1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String typeSiteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String wilayaLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String serviceDemandeur;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserV1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserDateV1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserV2;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserDateV2;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserOMV1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserOMDateV1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserOMV2;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String siteUserOMDateV2;

    private Integer typeAuditSiteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String typeAuditSiteLabel;

    private Integer currentCategoriesId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentCategoriesLabel;

    private int currentSatusId;
    private String currentSatusLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentSatusDescription;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentSatusStyleCSS;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentSatusIconCSS;


    // First Decision
    private Boolean firstVisit;
    private Integer firstDecisionId;
    private String firstDecisionLabel;
    private Date firstDecisionDate;
    private String firstDecisionEngineerSite;
    private String firstDecisionEngineerOM;

    // Second Decision
    private Boolean secondVisit;
    private Integer secondDecisionId;
    private String secondDecisionLabel;
    private Date secondDecisionDate;
    private String secondDecisionEngineerSite;
    private String secondDecisionEngineerOM;

    private List<AuditSiteLineDto> auditSiteLineDtoList = new ArrayList<>();
    private List<StatusAuditSiteDto> statusAuditSitesDtoList = new ArrayList<>();

}
