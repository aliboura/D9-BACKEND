package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
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
@NoArgsConstructor
@Entity
@Table(name = "AUDIT_SITE", schema = "DEPDATA")
public class AuditSite extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    private int regionId;

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

    @ManyToOne
    @JoinColumn(name = "FISRT_DECISION_ID")
    private Decision firstDecision;

    @Column(name = "FISRT_DECISION_DATE")
    private Date firstDecisionDate;

    @Column(name = "FIRST_DECISION_ENGINEER_SITE")
    private String firstDecisionEngineerSite;

    @Column(name = "FIRST_DECISION_ENGINEER_OM")
    private String firstDecisionEngineerOM;

    @ManyToOne
    @JoinColumn(name = "SECOND_DECISION_ID")
    private Decision secondDecision;

    @Column(name = "SECOND_DECISION_DATE")
    private Date secondDecisionDate;

    @Column(name = "SECOND_DECISION_ENGINEER_SITE")
    private String secondDecisionEngineerSite;

    @Column(name = "SECOND_DECISION_ENGINEER_OM")
    private String secondDecisionEngineerOM;

    @OneToMany(mappedBy = "auditSite", cascade = CascadeType.ALL)
    private List<AuditSiteLine> auditLineList = new ArrayList<>();

    @OneToMany(mappedBy = "auditSite", cascade = CascadeType.ALL)
    private List<StatusAuditSite> statusAuditSitesList = new ArrayList<>();
}
