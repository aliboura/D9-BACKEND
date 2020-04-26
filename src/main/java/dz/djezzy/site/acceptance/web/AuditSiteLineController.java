package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditStepsDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.business.services.AuditSiteLineService;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.business.services.StatusAuditSiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.AUDIT_SITE_LINE_API)
public class AuditSiteLineController extends GenericRestController<AuditSiteLineService, AuditSiteLine, AuditSiteLineDto, Integer> {

    private final AuditSiteService auditSiteService;
    private final AuditSiteLineService auditSiteLineService;
    private final StatusAuditSiteService statusAuditSiteService;

    public AuditSiteLineController(AuditSiteService auditSiteService, AuditSiteLineService auditSiteLineService, StatusAuditSiteService statusAuditSiteService) {
        this.auditSiteService = auditSiteService;
        this.auditSiteLineService = auditSiteLineService;
        this.statusAuditSiteService = statusAuditSiteService;
    }

    @PutMapping(value = "/goToNext", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AuditSiteDto goToNextSteps(@RequestBody AuditStepsDto steps) {
        return auditSiteService.goToNextSteps(steps);
    }

}
