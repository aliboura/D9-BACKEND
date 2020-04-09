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
    private String siteCode;
    private Integer wilayaId;
    private String regionId;
    private String repOwner;
    private String description;
    private String observation;
    private Boolean lastStep;
    private Boolean closed;

    private Integer typeAuditSiteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String typeAuditSiteLabel;

    private Integer currentCategoriesId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentCategoriesLabel;

    private int currentSatusId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentSatusLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentSatusStyleCSS;


    // First Decision
    private Boolean firstVisit;
    private Integer firstDecisionId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String firstDecisionLabel;
    private Date firstDecisionDate;
    private String firstDecisionEngineerSite;
    private String firstDecisionEngineerOM;

    // Second Decision
    private Boolean secondVisit;
    private Integer secondDecisionId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String secondDecisionLabel;
    private Date secondDecisionDate;
    private String secondDecisionEngineerSite;
    private String secondDecisionEngineerOM;

    private List<AuditSiteLineDto> auditSiteLineDtoList = new ArrayList<>();
    private List<StatusAuditSiteDto> statusAuditSitesDtoList = new ArrayList<>();
}
