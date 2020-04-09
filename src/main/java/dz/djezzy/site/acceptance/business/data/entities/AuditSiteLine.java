package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "AUDIT_SITE_LINE", schema = "DEPDATA")
public class AuditSiteLine extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "LABEL")
    private String label;

    @ManyToOne
    @JoinColumn(name = "SUB_CATEGORIES_ID")
    private SubCategories subCategories;

    @ManyToOne
    @JoinColumn(name = "CATEGORIES_ID")
    private Categories categories;

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

    public AuditSiteLine() {
        super();
    }

    public AuditSiteLine(int id, AuditSite auditSite, String observation, byte[] image, Decision firstDecision, Decision secondDecision) {
        this.id = id;
        this.auditSite = auditSite;
        this.observation = observation;
        this.image = image;
        this.firstDecision = firstDecision;
        this.secondDecision = secondDecision;
    }

}
