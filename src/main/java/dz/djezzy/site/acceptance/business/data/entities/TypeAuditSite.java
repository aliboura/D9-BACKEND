package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TYPE_AUDIT_SITE", schema = "DEPDATA")
public class TypeAuditSite implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "LABEL", unique = true)
    private String label;

    @NotNull
    @Column(name = "DESCRIPTION", unique = true)
    private String description;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "STATUS")
    private boolean status;

    @OneToMany(mappedBy = "typeAuditSite")
    private List<AuditSite> auditSiteList = new ArrayList<>();
}
