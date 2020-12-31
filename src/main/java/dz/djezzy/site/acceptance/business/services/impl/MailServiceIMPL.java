package dz.djezzy.site.acceptance.business.services.impl;

import com.sun.mail.util.MailConnectException;
import dz.djezzy.site.acceptance.business.data.dto.MailRequest;
import dz.djezzy.site.acceptance.business.data.dto.MailResponse;
import dz.djezzy.site.acceptance.business.services.MailService;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@AllArgsConstructor
@Service
@Transactional
public class MailServiceIMPL implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public MailResponse<String> sendNotifications(boolean secondVisit, MailRequest mailRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            String mailSubject = "D9 Notification";

            helper.setFrom("d9.notifications@djezzy.dz", "Site Transfers D9");
            helper.setTo(mailRequest.getMails());
            helper.setSubject(mailSubject);
            helper.setText(AppsUtils.getMailContent(secondVisit, mailRequest.getCodeSite(), mailRequest.getDateVisit(), mailRequest.getSiteAddress(), mailRequest.getEngineerSite(), mailRequest.getEngineerOM()), true);

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
    public MailResponse<String> sendNotifications(boolean secondVisit, MailRequest mailRequest, DataSource joint) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            String mailSubject = "D9 Notification";

            helper.setFrom("d9.notifications@djezzy.dz", "Site Transfers D9");
            helper.setTo(mailRequest.getMails());
            if (mailRequest.getCc() != null) {
                helper.setCc(mailRequest.getCc());
            }
            helper.setSubject(mailSubject);
            helper.addAttachment(mailRequest.getCodeSite() + ".pdf", joint);
            helper.setText(AppsUtils.getValidateMailContent(mailRequest.getCodeSite(), mailRequest.getDateVisit(), mailRequest.getSiteAddress(), mailRequest.getEngineerSite(), mailRequest.getEngineerOM(), mailRequest.getDecision()), true);

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
