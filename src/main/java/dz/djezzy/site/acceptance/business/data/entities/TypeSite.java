package dz.djezzy.site.acceptance.business.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TYPE_SITE", schema = "DEPDATA")
public class TypeSite implements Serializable {

    @Id
    @Column(name = "TYPE_SITE_ID", length = 10)
    private String id;

    @Basic
    @Column(name = "TYPE_DETAIL_EQUIP", length = 10)
    private String typeDetailEquip;

    @Basic
    @Column(name = "TYPE_INSTALLATION", length = 10)
    private String typeInstallation;

    @Basic
    @Column(name = "TYPE_SITE_LIB", length = 10)
    private String typeSiteLib;

    @OneToMany(mappedBy = "typeSite")
    private List<Site> sites = new ArrayList<>();

}