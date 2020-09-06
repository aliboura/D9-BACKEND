package dz.djezzy.site.acceptance.business.data.entities;

import dz.djezzy.site.acceptance.tools.BooleanToCharConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SITE", schema = "DEPDATA")
public class Site implements Serializable {


    @Id
    @Column(name = "SITE_ID")
    private Long id;

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
    private WilayaRegion wilaya;

    @OrderBy("id DESC")
    @OneToMany(mappedBy = "site")
    private List<AuditSite> auditSite = new ArrayList<>();

    @OrderBy("id DESC")
    @OneToMany(mappedBy = "site")
    private List<VisitPlanning> visitPlanningList = new ArrayList<>();

    public Site(String codeSite, String nomSite, String numSite) {
        this.codeSite = codeSite;
        this.nomSite = nomSite;
        this.numSite = numSite;
    }

    public String getUserV1() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerSiteV1();
        }
        return "-";
    }

    public String getUserV2() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerSiteV2();
        }
        return "-";
    }

    public String getUserOMV1() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerOMV1();
        }
        return "-";
    }

    public String getUserOMV2() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerOMV2();
        }
        return "-";
    }

    public Date getUserDateV1() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerSiteDateV1();
        }
        return null;
    }

    public Date getUserDateV2() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerSiteDateV2();
        }
        return null;
    }

    public Date getUserOMDateV1() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerOMDateV1();
        }
        return null;
    }

    public Date getUserOMDateV2() {
        if (!visitPlanningList.isEmpty()) {
            return visitPlanningList.get(0).getEngineerOMDateV1();
        }
        return null;
    }
}