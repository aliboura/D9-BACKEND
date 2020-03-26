package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dz.djezzy.site.acceptance.business.data.audit.AuditableDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuditSiteDto extends AuditableDto {

    @ReadOnlyProperty
    private int id;
    private Date auditDate;
    private String userId;
    private String siteCode;
    private int wilayaId;
    private String regionId;
    private String repOwner;
    private String description;
    private String observation;

    private int currentCategoriesId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentCategoriesLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CategoriesDto currentCategory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer nextCategoriesId;

    private int currentSatusId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentSatusLabel;

    // First Decision
    private Boolean firstStep;
    private Integer firstDecisionId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String firstDecisionLabel;
    private Date firstDecisionDate;
    private String firstDecisionEngineerSite;
    private String firstDecisionEngineerOM;

    // Second Decision
    private Boolean secondStep;
    private Integer secondDecisionId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String secondDecisionLabel;
    private Date secondDecisionDate;
    private String secondDecisionEngineerSite;
    private String secondDecisionEngineerOM;


    private List<AuditSiteLineDto> auditSiteLineDtoList = new ArrayList<>();
    private List<StatusAuditSiteDto> statusAuditSitesDtoList = new ArrayList<>();
}
