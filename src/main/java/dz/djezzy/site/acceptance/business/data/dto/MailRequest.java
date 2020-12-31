package dz.djezzy.site.acceptance.business.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailRequest implements Serializable {

    private String codeSite;
    private String DateVisit;
    private String siteAddress;
    private String engineerSite;
    private String engineerOM;
    private String decision;
    private String[] mails;
    private String[] cc;
}
