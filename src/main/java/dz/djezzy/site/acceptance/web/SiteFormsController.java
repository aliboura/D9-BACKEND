package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.dto.SiteFormsDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.SiteForms;
import dz.djezzy.site.acceptance.business.data.enums.DecisionEnum;
import dz.djezzy.site.acceptance.business.data.enums.StatusEnum;
import dz.djezzy.site.acceptance.business.services.SiteFormsService;
import dz.djezzy.site.acceptance.business.services.SiteService;
import dz.djezzy.site.acceptance.business.services.StatusService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(ApiConstant.SITE_FORMS_API)
public class SiteFormsController extends GenericRestController<SiteFormsService, SiteForms, SiteFormsDto, Integer> {

    private final SiteFormsService siteFormsService;
    private final SiteService siteService;
    private final StatusService statusService;

    public SiteFormsController(SiteFormsService siteFormsService, SiteService siteService, StatusService statusService) {
        this.siteFormsService = siteFormsService;
        this.siteService = siteService;
        this.statusService = statusService;
    }

    @GetMapping(params = {"codeSite"})
    public List<SiteFormsDto> get(@RequestParam("codeSite") String codeSite) {
        return siteFormsService.findByCodeSite(codeSite);
    }

    @PostMapping(value = "/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseStatus(HttpStatus.CREATED)
    SiteFormsDto createForms(@Valid @RequestBody() SiteFormsDto entity) {
        log.info("/files - entity: " + entity.toString());
        if (entity.getDecisionLabel() != null && entity.getCodeSite() != null) {
            SiteDto site = siteService.findByCodeSite(entity.getCodeSite());
            Optional<StatusDto> statusDto;
            if (entity.getDecisionLabel().equals(DecisionEnum.Conform.toString())) {
                statusDto = statusService.findByLabel(StatusEnum.Conform.toString());
                site.setPowerSupplyConform(true);
            } else if (entity.getDecisionLabel().equals(DecisionEnum.No_Conform.toString())) {
                statusDto = statusService.findByLabel(StatusEnum.No_Conform.toString());
                site.setPowerSupplyConform(false);
            } else {
                statusDto = statusService.findByLabel(StatusEnum.Accepted.toString());
                site.setPowerSupplyConform(false);
            }
            if (statusDto.isPresent()) {
                siteService.save(site);
            }
        }
        return super.create(entity);
    }
}