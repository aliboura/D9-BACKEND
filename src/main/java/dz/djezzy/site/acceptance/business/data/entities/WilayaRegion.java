package dz.djezzy.site.acceptance.business.data.entities;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "WILAYA_REGION", schema = "DEPDATA")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
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

    @Type(type = "list-array")
    @Column(name = "GROUP_SITES", columnDefinition = "text[]")
    private List<String> groupSites = new ArrayList<>();

    @Type(type = "list-array")
    @Column(name = "GROUP_OMS", columnDefinition = "text[]")
    private List<String> groupOMs = new ArrayList<>();

}
