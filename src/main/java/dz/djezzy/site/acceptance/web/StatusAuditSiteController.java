package dz.djezzy.site.acceptance.web;


import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import dz.djezzy.site.acceptance.business.services.StatusAuditSiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.STATUS_AUDIT_API)
public class StatusAuditSiteController extends GenericRestController<StatusAuditSiteService, StatusAuditSite, StatusAuditSiteDto, Long> {
}
