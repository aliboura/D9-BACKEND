package dz.djezzy.site.acceptance.business.data.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VAuditSiteDto implements Serializable {
    private Long id;
    private Date auditDate;
    private String code;
    private Boolean audited;
    private String region;
    private Integer wilayaId;
    private String wilaya;
    private Boolean firstVisit;
    private Boolean secondVisit;
    private Integer statusId;
    private String status;
}
