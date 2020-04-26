package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SqlResultSetMapping(
        name = "wilayaMapping",
        classes = @ConstructorResult(
                targetClass = Wilaya.class,
                columns = {@ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "label")}))
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SITE", schema = "NDEPDATA")
public class Site implements Serializable {

    @Id
    @Column(name = "SITE_ID")
    private long id;

    @Basic
    @Column(name = "CODE_SITE", length = 15)
    private String codeSite;

    @Basic
    @Column(name = "DATE_D1", columnDefinition = "DATE")
    private Date dateD1;

    @Basic
    @Column(name = "NOM_SITE", length = 50)
    private String nomSite;

    @Basic
    @Column(name = "NUM_SITE", length = 5)
    private String numSite;

    @Basic
    @Column(name = "PRIORITE_RADIO", length = 10)
    private String prioriteRadio;

    @Basic
    @Column(name = "REGION_ID", length = 1)
    private String regionId;

    @Basic
    @Column(name = "SERVICE_DEMANDEUR", length = 10)
    private String serviceDemandeur;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "AUDITED")
    private Boolean audited;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "POWER_SUPPLY_CONFORM")
    private Boolean powerSupplyConform;

    @ManyToOne
    @JoinColumn(name = "TYPE_SITE_ID")
    private TypeSite typeSite;

    @ManyToOne
    @JoinColumn(name = "WILAYA_ID")
    private Wilaya wilaya;

    public Site(String codeSite, String nomSite, String numSite) {
        this.codeSite = codeSite;
        this.nomSite = nomSite;
        this.numSite = numSite;
    }
}