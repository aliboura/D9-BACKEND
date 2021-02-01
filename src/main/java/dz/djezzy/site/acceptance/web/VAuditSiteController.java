package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.VAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.VAuditSite;
import dz.djezzy.site.acceptance.business.services.VAuditSiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.V_AUDIT_SITE_API)
public class VAuditSiteController extends GenericRestController<VAuditSiteService, VAuditSite, VAuditSiteDto, Long> {
}
