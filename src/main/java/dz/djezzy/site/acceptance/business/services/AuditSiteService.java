package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditStepsDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface AuditSiteService extends GenericService<AuditSite, AuditSiteDto, Integer> {


    StatusAuditSiteDto createStatusAudit(AuditSiteDto entity);
    Optional<StatusDto> checkStatus(AuditSiteDto entity);
    Optional<StatusDto> checkConfirmedStatus(AuditSiteDto entity);
    Optional<StatusDto> setStatusConfirmedValues(String decision);
    Optional<StatusDto> setStatusDecision(String decisionEngineerSite, String decisionEngineerOM);
    AuditSiteDto setCurrentStatus(AuditSiteDto entity, String label);
    AuditSiteDto goToNextSteps(@RequestBody AuditStepsDto steps);
}
