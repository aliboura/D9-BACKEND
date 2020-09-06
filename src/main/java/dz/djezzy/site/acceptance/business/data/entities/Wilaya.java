package dz.djezzy.site.acceptance.business.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "DEPDATA", name = "WILAYA")
public class Wilaya implements Serializable {

    @Id
    @Column(name = "WILAYA_ID")
    private int id;

    @Basic
    @Column(name = "WILAYA_LIB", length = 30)
    private String label;

    @OneToMany(mappedBy = "wilaya")
    private Set<Commune> communesList = new HashSet<Commune>();

    @Basic
    @Column(name = "REGION_ID", length = 1)
    private String regionId;

    @OneToMany(mappedBy = "wilaya")
    private List<Site> sitesList = new ArrayList<>();
}