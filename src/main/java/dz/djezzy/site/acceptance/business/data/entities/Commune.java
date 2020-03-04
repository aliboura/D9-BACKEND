package dz.djezzy.site.acceptance.business.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "NDEPDATA", name = "COMMUNE")
public class Commune implements Serializable {

    @Id
    @Column(name = "COMMUNE_ID")
    private int id;

    @Basic
    @Column(name = "COMMUNE_LIB", length = 30)
    private String label;

    @ManyToOne
    @JoinColumn(name = "WILAYA_ID")
    private Wilaya wilaya;

}