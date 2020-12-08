package dz.djezzy.site.acceptance.business.data.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuditSecondVisitDto implements Serializable {

    private AuditSiteDto auditSite;
    private List<AuditSiteLineDto> auditSiteLines;
}
