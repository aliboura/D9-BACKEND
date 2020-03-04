package dz.djezzy.site.acceptance.business.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "NDEPDATA", name = "WILAYA")
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