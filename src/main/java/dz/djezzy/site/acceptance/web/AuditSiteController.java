package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import dz.djezzy.site.acceptance.business.data.enums.StatusEnum;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.business.services.StatusAuditSiteService;
import dz.djezzy.site.acceptance.business.services.StatusService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.AUDIT_SITE_API)
public class AuditSiteController extends GenericRestController<AuditSiteService, AuditSite, AuditSiteDto, Integer> {

    private final StatusAuditSiteService statusAuditSiteService;
    private final StatusService statusService;

    public AuditSiteController(StatusAuditSiteService statusAuditSiteService, StatusService statusService) {
        this.statusAuditSiteService = statusAuditSiteService;
        this.statusService = statusService;
    }


    @Override
    @PostMapping
    public AuditSiteDto create(@RequestBody AuditSiteDto entity) {
        AuditSiteDto saved = super.create(entity);
        StatusAuditSiteDto dto = createStatusAudit(saved);
        statusAuditSiteService.save(dto);
        return saved;
    }

    @Override
    @PutMapping
    public AuditSiteDto update(@RequestBody AuditSiteDto entity) {
        Optional<StatusDto> status = checkStatus(entity);
        if (status.isPresent()) {
            entity.setCurrentSatusId(status.get().getId());
            entity.setCurrentSatusLabel(status.get().getLabel());
        }

        super.update(entity);
        Boolean exist = false;
        if (!entity.getStatusAuditSitesDtoList().isEmpty()) {
            entity.getStatusAuditSitesDtoList().forEach(x -> x.setCurrent(false));
            StatusAuditSiteDto state = entity.getStatusAuditSitesDtoList().stream().filter(x -> x.getStatusId() == entity.getCurrentSatusId())
                    .findAny()
                    .orElse(null);
            exist = state == null;
        }
        if (exist) {
            StatusAuditSiteDto statusAuditSiteDto = createStatusAudit(entity);
            statusAuditSiteService.save(statusAuditSiteDto);
        }
        return entity;
    }

    private StatusAuditSiteDto createStatusAudit(AuditSiteDto entity) {
        StatusAuditSiteDto statusAuditSite = new StatusAuditSiteDto();
        statusAuditSite.setStatusId(entity.getCurrentSatusId());
        statusAuditSite.setStatusLabel(entity.getCurrentSatusLabel());
        statusAuditSite.setAuditSiteId(entity.getId());
        statusAuditSite.setCurrent(true);
        statusAuditSite.setStatusDate(new Date());
        statusAuditSite.setDescription(entity.getSiteCode() + " - " + entity.getCurrentSatusLabel());
        return statusAuditSite;
    }

    private Optional<StatusDto> checkStatus(AuditSiteDto entity) {
        if (entity.getCurrentSatusLabel().equals(StatusEnum.In_Progress.toString())) {
            if (entity.getFirstDecisionDate() != null
                    && entity.getFirstDecisionEngineerSite() != null
                    && entity.getFirstDecisionEngineerOM() != null) {
                return checkConfirmedStatus(entity);
            } else {
                return Optional.empty();
            }
        } else {
            if (entity.getSecondDecisionDate() != null
                    && entity.getSecondDecisionEngineerSite() != null
                    && entity.getSecondDecisionEngineerOM() != null) {
                return checkConfirmedStatus(entity);
            } else {
                return Optional.empty();
            }
        }
    }

    private Optional<StatusDto> checkConfirmedStatus(AuditSiteDto entity) {
        if (entity.getFirstVisit() && !entity.getSecondVisit()) {
            return setStatusConfirmedValues(entity.getFirstDecisionId());
        } else if (entity.getFirstVisit() && entity.getSecondVisit()) {
            return setStatusConfirmedValues(entity.getSecondDecisionId());
        }
        return null;
    }

    private Optional<StatusDto> setStatusConfirmedValues(int decisionId) {
        if (decisionId == 5) {
            return statusService.findByLabel(StatusEnum.Conform.toString());
        } else if (decisionId == 6) {
            return statusService.findByLabel(StatusEnum.Accepted.toString());
        } else if (decisionId == 7) {
            return statusService.findByLabel(StatusEnum.No_Conform.toString());
        }
        return null;
    }

    private Optional<StatusDto> setStatusDecision(String decisionEngineerSite, String decisionEngineerOM) {
        if (decisionEngineerSite == null
                && decisionEngineerOM == null) {
            return null;
        } else if (decisionEngineerSite != null
                && decisionEngineerOM == null) {
            return statusService.findByLabel(StatusEnum.Validate_Site.toString());
        } else if (decisionEngineerSite != null) {
            return statusService.findByLabel(StatusEnum.Validate_OM.toString());
        }
        return null;
    }

}
