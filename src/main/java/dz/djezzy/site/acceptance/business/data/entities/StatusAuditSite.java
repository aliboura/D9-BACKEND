package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.business.data.audit.Auditable;
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
    private Long id;

    @NotNull
    @Column(name = "STATUS_DATE")
    private Date statusDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CURRENT")
    private Character current;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "AUDIT_SITE_ID")
    private AuditSite auditSite;

}
