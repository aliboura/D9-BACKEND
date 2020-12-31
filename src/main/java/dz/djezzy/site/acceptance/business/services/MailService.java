package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.MailRequest;
import dz.djezzy.site.acceptance.business.data.dto.MailResponse;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {

    MailResponse<String> sendNotifications(boolean secondVisit, MailRequest mailRequest) throws UnsupportedEncodingException, MessagingException;

    MailResponse<String> sendNotifications(boolean secondVisit, MailRequest mailRequest, DataSource joint) throws UnsupportedEncodingException, MessagingException;
}
