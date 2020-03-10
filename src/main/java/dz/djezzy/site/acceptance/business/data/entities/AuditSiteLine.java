package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AUDIT_SITE_LINE", schema = "DEPDATA")
public class AuditSiteLine extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "AUDIT_SITE_ID", nullable = false)
    private AuditSite auditSite;

    @Column(name = "OBSERVATION")
    private String observation;

    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "FIRST_DECISION_ID")
    private Decision firstDecision;

    @ManyToOne
    @JoinColumn(name = "SECOND_DECISION_ID")
    private Decision secondDecision;

}
