package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditStepsDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.enums.DecisionEnum;
import dz.djezzy.site.acceptance.business.data.enums.StatusEnum;
import dz.djezzy.site.acceptance.business.repository.AuditSiteRepository;
import dz.djezzy.site.acceptance.business.services.AuditSiteLineService;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import dz.djezzy.site.acceptance.business.services.StatusService;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class AuditSiteServiceIMPL extends GenericServiceImpl<AuditSiteRepository, AuditSite, AuditSiteDto, Integer>
        implements AuditSiteService {

    private final StatusService statusService;
    private final AuditSiteRepository auditSiteRepository;
    private final AuditSiteLineService auditSiteLineService;

    @Override
    public StatusAuditSiteDto createStatusAudit(AuditSiteDto entity) {
        StatusAuditSiteDto statusAuditSite = new StatusAuditSiteDto();
        statusAuditSite.setStatusId(entity.getCurrentSatusId());
        statusAuditSite.setStatusLabel(entity.getCurrentSatusLabel());
        statusAuditSite.setAuditSiteId(entity.getId());
        statusAuditSite.setUsername(AppsUtils.getUserPrincipal());
        statusAuditSite.setCurrent(true);
        if (entity.getCurrentSatusLabel().equals(StatusEnum.Conform.toString()) ||
                entity.getCurrentSatusLabel().equals(StatusEnum.Accepted.toString())) {
            statusAuditSite.setLast(true);
        } else {
            statusAuditSite.setLast(false);
        }
        statusAuditSite.setStatusDate(new Date());
        statusAuditSite.setDescription(entity.getSiteCode() + " - " + entity.getCurrentSatusLabel());
        entity.getStatusAuditSitesDtoList().add(statusAuditSite);
        return statusAuditSite;
    }

    @Override
    public Optional<StatusDto> checkStatus(AuditSiteDto entity) {
        String check = checkVisit(entity);
        if (check != null) {
            if (check.equals("V1-Final") || check.equals("V2-Final")) {
                return checkConfirmedStatus(entity);
            } else if (check.equals("V1-Valid")) {
                return setStatusDecision(entity.getFirstDecisionEngineerSite(), entity.getFirstDecisionEngineerOM());
            } else if (check.equals("V2-Valid")) {
                return setStatusDecision(entity.getSecondDecisionEngineerSite(), entity.getSecondDecisionEngineerOM());
            }
        }
        return Optional.empty();
    }

    private String checkVisit(AuditSiteDto entity) {
        if (!entity.getSecondVisit()
                && entity.getFirstVisit()
                && entity.getFirstDecisionDate() != null
                && entity.getFirstDecisionEngineerSite() != null
                && entity.getFirstDecisionEngineerOM() != null) {
            return "V1-Final";
        } else if (!entity.getSecondVisit()
                && entity.getFirstVisit()
                && entity.getFirstDecisionDate() != null
                && entity.getFirstDecisionEngineerSite() != null
                && entity.getFirstDecisionEngineerOM() == null) {
            return "V1-Valid";
        } else if (entity.getSecondVisit()
                && entity.getSecondCheck()
                && entity.getSecondDecisionId() != null
                && entity.getSecondDecisionEngineerSite() != null
                && entity.getSecondDecisionEngineerOM() != null) {
            return "V2-Final";
        } else if (entity.getSecondVisit()
                && entity.getSecondCheck()
                && entity.getSecondDecisionId() != null
                && entity.getSecondDecisionEngineerSite() != null
                && entity.getSecondDecisionEngineerOM() == null) {
            return "V2-Valid";
        }
        return null;
    }

    @Override
    public Optional<StatusDto> checkConfirmedStatus(AuditSiteDto entity) {
        if (entity.getFirstVisit() && !entity.getSecondVisit()) {
            return setStatusConfirmedValues(entity.getFirstDecisionLabel());
        } else if (entity.getFirstVisit() && entity.getSecondVisit()) {
            return setStatusConfirmedValues(entity.getSecondDecisionLabel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<StatusDto> setStatusConfirmedValues(String decision) {
        if (decision.equals(DecisionEnum.Conform.toString())) {
            return statusService.findByLabel(StatusEnum.Conform.toString());
        } else if (decision.equals(DecisionEnum.Accepted.toString())) {
            return statusService.findByLabel(StatusEnum.Accepted.toString());
        } else if (decision.equals(DecisionEnum.No_Conform.toString())) {
            return statusService.findByLabel(StatusEnum.No_Conform.toString());
        }
        return Optional.empty();
    }

    @Override
    public Optional<StatusDto> setStatusDecision(String decisionEngineerSite, String decisionEngineerOM) {
        if (decisionEngineerSite != null
                && decisionEngineerOM == null) {
            return statusService.findByLabel(StatusEnum.Validate_Site.toString());
        }
        return Optional.empty();
    }

    @Override
    public AuditSiteDto setCurrentStatus(AuditSiteDto entity, String label) {
        Optional<StatusDto> statusDto;
        if (!entity.getCurrentSatusLabel().equals(label)) {
            statusDto = statusService.findByLabel(label);
            if (statusDto.isPresent()) {
                entity.setCurrentSatusId(statusDto.get().getId());
                entity.setCurrentSatusLabel(statusDto.get().getLabel());
            }
        }
        return entity;
    }

    @Transactional
    @Override
    public AuditSiteDto goToNextSteps(AuditStepsDto steps) {
        if (steps.getAuditSiteLineList() != null) {
            auditSiteLineService.saveAll(steps.getAuditSiteLineList());
        }
        if (steps.getEditCategories()) {
            AuditSiteDto auditSiteDtoOpt = getOne(steps.getAuditSite().getId());
            if (auditSiteDtoOpt != null
                    && steps.getCurrentCategory() != null
                    && steps.getCurrentCategory().getNextCatId() != null) {
                auditSiteDtoOpt.setCurrentCategoriesId(steps.getCurrentCategory().getNextCatId());
                return save(auditSiteDtoOpt);
            }
        }
        if (steps.getCurrentCategory() != null && steps.getCurrentCategory().getLast()) {
            steps.getAuditSite().setLastStep(true);
        }
        if (steps.getAuditSite().getLastStep()
                && !steps.getAuditSite().getFirstVisit()
                && !steps.getAuditSite().getSecondVisit()) {
            AuditSiteDto doSave = setCurrentStatus(steps.getAuditSite(), StatusEnum.In_Progress_Validate.toString());
            doSave.setFirstCheckDate(new Date());
            return save(doSave);
        }
        return steps.getAuditSite();
    }

    @Override
    public Page<AuditSiteDto> findByEngineerSite(String username, Pageable pageable) {
        return auditSiteRepository.findByEngineerSite(username, pageable).map(data -> asDto(data));
    }

    @Override
    public List<AuditSiteDto> findByEngineerSite(String username) {
        return asDto(auditSiteRepository.findByEngineerSite(username));
    }

    @Override
    public List<AuditSiteDto> findByEngineerSite(String username, Integer statusId) {
        return asDto(auditSiteRepository.findByEngineerSite(username, statusId));
    }

    @Override
    public List<AuditSiteDto> findByEngineerSite(String username, Integer statusId, Date fromDate, Date toDate) {
        return asDto(auditSiteRepository.findByEngineerSite(username, statusId, fromDate, toDate));
    }

}
