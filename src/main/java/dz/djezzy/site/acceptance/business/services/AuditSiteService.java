package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.*;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AuditSiteService extends GenericService<AuditSite, AuditSiteDto, Integer> {


    StatusAuditSiteDto createStatusAudit(AuditSiteDto entity);

    Optional<StatusDto> checkStatus(AuditSiteDto entity);

    Optional<StatusDto> checkConfirmedStatus(AuditSiteDto entity);

    Optional<StatusDto> setStatusConfirmedValues(String decision);

    Optional<StatusDto> setStatusDecision(String decisionEngineerSite, String decisionEngineerOM);

    AuditSiteDto setCurrentStatus(AuditSiteDto entity, String label);

    AuditSiteDto goToNextSteps(@RequestBody AuditStepsDto steps);

    Page<AuditSiteDto> findByEngineerSite(String username, Pageable pageable);

    List<AuditSiteDto> findByEngineerSite(String username);

    List<AuditSiteDto> findByEngineerSite(String username, Integer statusId);

    List<AuditSiteDto> findByEngineerSite(String username, Integer statusId, Date fromDate, Date toDate);

    Iterable<AuditSiteLineCSV> saveAllCsvLines(Iterable<Object> iterable, AuditSiteDto auditSiteDto);

}
