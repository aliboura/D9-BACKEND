package dz.djezzy.site.acceptance.business.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuditStepsDto implements Serializable {
    private AuditSiteDto auditSite;
    private Boolean editCategories;
    private Iterable<AuditSiteLineDto> auditSiteLineList;
}
