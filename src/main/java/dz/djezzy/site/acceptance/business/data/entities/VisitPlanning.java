package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VISIT_PLANNING", schema = "DEPDATA")
public class VisitPlanning extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VISIT_PLANNING_SEQ")
    @SequenceGenerator(sequenceName = "VISIT_PLANNING_SEQUENCE_ID", allocationSize = 1, name = "VISIT_PLANNING_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SITE_ID", nullable = false, unique = true)
    private Site site;

    @Column(name = "ENGINEER_SITE_V1", length = 100)
    private String engineerSiteV1;
    @Column(name = "ENGINEER_SITE_DATE_V1")
    private Date engineerSiteDateV1;
    @Column(name = "ENGINEER_OM_V1", length = 100)
    private String engineerOMV1;
    @Column(name = "ENGINEER_OM_DATE_V1")
    private Date engineerOMDateV1;
    @Column(name = "ENGINEER_SITE_V2", length = 100)
    private String engineerSiteV2;
    @Column(name = "ENGINEER_SITE_DATE_V2")
    private Date engineerSiteDateV2;
    @Column(name = "ENGINEER_OM_V2", length = 100)
    private String engineerOMV2;
    @Column(name = "ENGINEER_OM_DATE_V2")
    private Date engineerOMDateV2;

    public List<AuditSite> getD9Dorms() {
        if (site != null && !site.getAuditSite().isEmpty()) {
            return site.getAuditSite().stream().filter(x -> x.getTypeAuditSite() != null &&
                    x.getTypeAuditSite().getLabel().equals("Formulaire D9")).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public boolean isFirstVisit() {
        if (site != null) {
            List<AuditSite> list = getD9Dorms();
            return !list.isEmpty() ? list.get(0).getFirstVisit() : false;
        }
        return false;
    }

    public boolean isSecondVisit() {
        if (site != null) {
            List<AuditSite> list = getD9Dorms();
            return !list.isEmpty() ? list.get(0).getSecondVisit() : false;
        }
        return false;
    }
}
