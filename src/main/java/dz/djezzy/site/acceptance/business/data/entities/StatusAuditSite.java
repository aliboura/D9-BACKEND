package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STATUS_AUDIT_SITE", schema = "DEPDATA")
public class StatusAuditSite extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATUS_AUDIT_SITE_SEQ")
    @SequenceGenerator(sequenceName = "STATUS_AUDIT_SITE_SEQUENCE_ID", allocationSize = 1, name = "STATUS_AUDIT_SITE_SEQ")
    private Integer id;

    @NotNull
    @Column(name = "STATUS_DATE")
    private Date statusDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "CURRENT")
    private boolean current;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "LAST")
    private boolean last;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "AUDIT_SITE_ID")
    private AuditSite auditSite;

}
