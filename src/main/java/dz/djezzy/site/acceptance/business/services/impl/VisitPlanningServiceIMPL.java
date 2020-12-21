package dz.djezzy.site.acceptance.business.services.impl;

import com.sun.mail.util.MailConnectException;
import dz.djezzy.site.acceptance.business.data.dto.MailRequest;
import dz.djezzy.site.acceptance.business.data.dto.MailResponse;
import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import dz.djezzy.site.acceptance.business.repository.VisitPlanningRepository;
import dz.djezzy.site.acceptance.business.services.VisitPlanningService;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class VisitPlanningServiceIMPL extends GenericServiceImpl<VisitPlanningRepository, VisitPlanning, VisitPlanningDto, Long>
        implements VisitPlanningService {

    private final VisitPlanningRepository visitPlanningRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public Page<VisitPlanningDto> findByCities(List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findByCities(citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Optional<VisitPlanningDto> findBySiteId(Long id) {
        Optional<VisitPlanning> opt = visitPlanningRepository.findBySiteId(id);
        return opt.isPresent() ? Optional.ofNullable(asDto(opt.get())) : Optional.empty();
    }

    @Override
    public Page<VisitPlanningDto> findByCode(String code, List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findByCode(code, citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findFirstVisitPlannings(String engineerSiteV1, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findFirstVisitPlannings(engineerSiteV1, fromDate, toDate, citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findSecondVisitPlannings(String engineerSiteV2, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findSecondVisitPlannings(engineerSiteV2, fromDate, toDate, citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findEngineerPlannings(String username, Pageable pageable) {
        return visitPlanningRepository.findEngineerPlannings(username, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findOMPlannings(String username, Pageable pageable) {
        return visitPlanningRepository.findOMPlannings(username, pageable).map(data -> asDto(data));
    }

    @Override
    public Integer countEngineer(String username, Date date) {
        return visitPlanningRepository.countEngineer(username, date);
    }

    @Override
    public Integer countEngineerOM(String username, Date date) {
        return visitPlanningRepository.countEngineerOM(username, date);
    }

    @Transactional
    public MailResponse<String> sendV1Notifications(MailRequest mailRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            String mailSubject = "D9 Notification";

            helper.setFrom("d9.notifications@djezzy.dz", "Site Transfers D9");
            helper.setTo(mailRequest.getMails());
            helper.setSubject(mailSubject);
            helper.setText(AppsUtils.getMailContent(false, mailRequest.getCodeSite(), mailRequest.getDateVisit(), mailRequest.getSiteAddress(), mailRequest.getIngenieurSite(), mailRequest.getIngenieurOM()), true);

            ClassPathResource resource = new ClassPathResource("images/mail-logo.png");
            helper.addInline("logoImage", resource);

            javaMailSender.send(message);
            return new MailResponse<>(true, "OK");
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        }
    }

    @Override
    public MailResponse<String> sendV2Notifications(MailRequest mailRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            String mailSubject = "D9 Notification";
            helper.setFrom("d9.notifications@djezzy.dz", "Site Transfers D9");
            helper.setTo(mailRequest.getMails());
            helper.setSubject(mailSubject);
            helper.setText(AppsUtils.getMailContent(true, mailRequest.getCodeSite(), mailRequest.getDateVisit(), mailRequest.getSiteAddress(), mailRequest.getIngenieurSite(), mailRequest.getIngenieurOM()), true);

            ClassPathResource resource = new ClassPathResource("images/mail-logo.png");
            helper.addInline("logoImage", resource);

            javaMailSender.send(message);
            return new MailResponse<>(true, "OK");
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        }
    }
}
