package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditStepsDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.enums.StatusEnum;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.business.services.StatusAuditSiteService;
import dz.djezzy.site.acceptance.business.services.StatusService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(ApiConstant.AUDIT_SITE_API)
public class AuditSiteController extends GenericRestController<AuditSiteService, AuditSite, AuditSiteDto, Integer> {

    private final StatusAuditSiteService statusAuditSiteService;
    private final StatusService statusService;
    private final AuditSiteService auditSiteService;

    public AuditSiteController(StatusAuditSiteService statusAuditSiteService, StatusService statusService, AuditSiteService auditSiteService) {
        this.statusAuditSiteService = statusAuditSiteService;
        this.statusService = statusService;
        this.auditSiteService = auditSiteService;
    }


    @Transactional
    @Override
    @PostMapping
    public AuditSiteDto create(@RequestBody AuditSiteDto entity) {
        AuditSiteDto saved = super.create(entity);
        StatusAuditSiteDto dto = auditSiteService.createStatusAudit(saved);
        statusAuditSiteService.save(dto);
        return saved;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ENGINEER_SITE','ROLE_ENGINEER_OM')")
    @Transactional
    @PutMapping(value = "/goToFinish")
    public AuditSiteDto goToFinish(@RequestBody AuditStepsDto steps) {
        AuditSiteDto doSaved = auditSiteService.goToNextSteps(steps);
        Optional<StatusDto> status = auditSiteService.checkStatus(doSaved);
        if (status.isPresent()) {
            doSaved.setCurrentSatusId(status.get().getId());
            doSaved.setCurrentSatusLabel(status.get().getLabel());
            if (status.get().getLabel().equals(StatusEnum.Conform.toString())) {
                doSaved.setClosed(true);
            }
            if (status.get().getLabel().equals(StatusEnum.Validate_Site.toString())) {
                if (doSaved.getFirstVisit() && !doSaved.getSecondVisit()) {
                    doSaved.setFirstVisit(false);
                } else if (doSaved.getFirstVisit() && doSaved.getSecondVisit()) {
                    doSaved.setSecondVisit(false);
                }
            }
        }

        AuditSiteDto saved = auditSiteService.save(doSaved);
        if (checkExistStatus(saved)) {
            changeCurrentStatus(doSaved);
            StatusAuditSiteDto statusAuditSiteDto = auditSiteService.createStatusAudit(saved);
            statusAuditSiteService.save(statusAuditSiteDto);
        }
        return saved;
    }

    private void changeCurrentStatus(AuditSiteDto siteDto) {
        if (!siteDto.getStatusAuditSitesDtoList().isEmpty()) {
            siteDto.getStatusAuditSitesDtoList().forEach(x -> {
                x.setCurrent(false);
                x.setLast(false);
                statusAuditSiteService.save(x);
            });
        }
    }

    private boolean checkExistStatus(AuditSiteDto siteDto) {
        StatusAuditSiteDto state = siteDto.getStatusAuditSitesDtoList().stream()
                .filter(x -> x.getStatusId() == siteDto.getCurrentSatusId() && x.isCurrent())
                .findAny()
                .orElse(null);
        return state == null;
    }

    @Transactional
    @PutMapping("/second-visit")
    public ResponseEntity<AuditSiteDto> secondVisit(@RequestBody AuditSiteDto entity) {
        AuditSiteDto doSave = auditSiteService.setCurrentStatus(entity, StatusEnum.In_Progress_Validate_V2.toString());
        doSave.setSecondCheckDate(new Date());
        doSave.setSecondCheck(true);
        AuditSiteDto saved = auditSiteService.save(doSave);
        if (checkExistStatus(saved)) {
            changeCurrentStatus(saved);
            StatusAuditSiteDto dto = auditSiteService.createStatusAudit(saved);
            statusAuditSiteService.save(dto);
        }
        return ResponseEntity.ok(saved);
    }


}
