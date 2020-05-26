package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(name = "SITE_ID", nullable = false)
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
}
