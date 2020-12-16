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
    private String ingenieurSite;
    private String ingenieurOM;
    private String[] mails;
}
