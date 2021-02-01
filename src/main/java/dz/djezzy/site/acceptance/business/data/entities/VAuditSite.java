package dz.djezzy.site.acceptance.business.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "v_audit_site", schema = "DEPDATA")
public class VAuditSite implements Serializable {

    @Id
    @Column(name = "audit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date auditDate;

    @Column(name = "code")
    private String code;

    @Column(name = "audited")
    private Boolean audited;

    @Column(name = "region")
    private String region;

    @Column(name = "wilaya_id")
    private Integer wilayaId;

    @Column(name = "wilaya")
    private String wilaya;

    @Column(name = "first_visit")
    private Boolean firstVisit;

    @Column(name = "second_visit")
    private Boolean secondVisit;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status")
    private String status;
}
