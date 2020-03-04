package dz.djezzy.site.acceptance.business.data.audit;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class AuditableDto implements Serializable {

    private String createdBy;
    private Date creationDate;
    private String modifiedBy;
    private Date modificationDate;
}
