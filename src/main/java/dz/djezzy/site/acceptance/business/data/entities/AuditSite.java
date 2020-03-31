package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "AUDIT_SITE", schema = "DEPDATA")
public class AuditSite extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "DATE_AUDIT")
    private Date auditDate;

    @Column(name = "USER_ID")
    private String userId;

    @NotNull
    @Column(name = "SITE_CODE")
    private String siteCode;

    @Column(name = "WILAYA_ID")
    private int wilayaId;

    @Column(name = "REGION_ID")
    private String regionId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OBSERVATION")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "CATEGORIE_CURRENT_ID")
    private Categories currentCategories;

    @ManyToOne
    @JoinColumn(name = "STATUS_CURRENT_ID", nullable = false)
    private Status currentSatus;

    @Convert(converter = BooleanToCharConverter.class)
    @JoinColumn(name = "LAST_STEP")
    private Boolean lastStep;

    @Convert(converter = BooleanToCharConverter.class)
    @JoinColumn(name = "FIRST_VISIT")
    private Boolean firstVisit;

    @ManyToOne
    @JoinColumn(name = "FISRT_DECISION_ID")
    private Decision firstDecision;

    @Column(name = "FISRT_DECISION_DATE")
    private Date firstDecisionDate;

    @Column(name = "FIRST_DECISION_ENGINEER_SITE")
    private String firstDecisionEngineerSite;

    @Column(name = "FIRST_DECISION_ENGINEER_OM")
    private String firstDecisionEngineerOM;

    @Convert(converter = BooleanToCharConverter.class)
    @JoinColumn(name = "SECOND_VISIT")
    private Boolean secondVisit;

    @Convert(converter = BooleanToCharConverter.class)
    @JoinColumn(name = "CLOSED")
    private Boolean closed;

    @ManyToOne
    @JoinColumn(name = "SECOND_DECISION_ID")
    private Decision secondDecision;

    @Column(name = "SECOND_DECISION_DATE")
    private Date secondDecisionDate;

    @Column(name = "SECOND_DECISION_ENGINEER_SITE")
    private String secondDecisionEngineerSite;

    @Column(name = "SECOND_DECISION_ENGINEER_OM")
    private String secondDecisionEngineerOM;

    @OneToMany(mappedBy = "auditSite")
    private List<AuditSiteLine> auditLineList = new ArrayList<>();

    @OneToMany(mappedBy = "auditSite")
    private List<StatusAuditSite> statusAuditSitesList = new ArrayList<>();

    public AuditSite() {
    }

    public AuditSite(int id, Date auditDate, String userId, String siteCode, int wilayaId, String regionId, String description, String observation) {
        this.id = id;
        this.auditDate = auditDate;
        this.userId = userId;
        this.siteCode = siteCode;
        this.wilayaId = wilayaId;
        this.regionId = regionId;
        this.description = description;
        this.observation = observation;
    }
}
