package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditStepsDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.business.services.AuditSiteLineService;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.AUDIT_SITE_LINE_API)
public class AuditSiteLineController extends GenericRestController<AuditSiteLineService, AuditSiteLine, AuditSiteLineDto, Integer> {

    @Autowired
    private AuditSiteService auditSiteService;

    @Autowired
    private AuditSiteLineService auditSiteLineService;

    @PutMapping(value = "/goToNext", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AuditSiteDto goToNextSteps(@RequestBody AuditStepsDto steps) {
        if (steps.getAuditSiteLineList() != null) {
            auditSiteLineService.saveAll(steps.getAuditSiteLineList());
        }
        if (steps.getEditCategories()) {
            AuditSiteDto auditSiteDtoOpt = auditSiteService.getOne(steps.getAuditSite().getId());
            if (auditSiteDtoOpt != null
                    && steps.getCurrentCategory() != null
                    && steps.getCurrentCategory().getNextCatId() != null) {
                auditSiteDtoOpt.setCurrentCategoriesId(steps.getCurrentCategory().getNextCatId());
                return auditSiteService.save(auditSiteDtoOpt);
            }
        }
        return steps.getAuditSite();
    }

}
