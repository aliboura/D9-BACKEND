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
@Table(name = "WILAYA_REGION", schema = "DEPDATA")
public class WilayaRegion {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Basic
    @Column(name = "LABEL", length = 30)
    private String label;

    @Basic
    @Column(name = "REGION_ID", length = 1)
    private String regionId;

}
