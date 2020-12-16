package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.MailRequest;
import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VisitPlanningService extends GenericService<VisitPlanning, VisitPlanningDto, Long> {

    Page<VisitPlanningDto> findByCities(List<Integer> citiesIds, Pageable pageable);

    Optional<VisitPlanningDto> findBySiteId(Long id);

    Page<VisitPlanningDto> findByCode(String code, List<Integer> citiesIds, Pageable pageable);

    Page<VisitPlanningDto> findFirstVisitPlannings(String engineerSiteV1, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable);

    Page<VisitPlanningDto> findSecondVisitPlannings(String engineerSiteV2, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable);

    Page<VisitPlanningDto> findEngineerPlannings(String username, Pageable pageable);

    Page<VisitPlanningDto> findOMPlannings(String username, Pageable pageable);

    Integer countEngineer(String username, Date date);

    Integer countEngineerOM(String username, Date date);

    String sendV1Notifications(MailRequest mailRequest) throws UnsupportedEncodingException, MessagingException;

    String sendV2Notifications(MailRequest mailRequest) throws UnsupportedEncodingException, MessagingException;

}
