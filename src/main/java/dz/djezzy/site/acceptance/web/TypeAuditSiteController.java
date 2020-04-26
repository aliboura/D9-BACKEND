package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.TypeAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.TypeAuditSite;
import dz.djezzy.site.acceptance.business.services.TypeAuditSiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.TYPE_AUDIT_SITE_API)
public class TypeAuditSiteController extends GenericRestController<TypeAuditSiteService, TypeAuditSite, TypeAuditSiteDto, Integer> {
}
