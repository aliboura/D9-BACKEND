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
    private String typeAuditSiteLabel;

    private Integer currentCategoriesId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currentCategoriesLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CategoriesDto currentCategory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer nextCategoriesId;

    private int currentSatusId;
    private String currentSatusLabel;
    private String currentSatusStyleCSS;


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
