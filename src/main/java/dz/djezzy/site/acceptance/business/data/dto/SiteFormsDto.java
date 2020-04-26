package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dz.djezzy.site.acceptance.tools.BytesDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SiteFormsDto implements Serializable {

    private Integer id;
    private String codeSite;
    private String fileName;
    private String fileType;
    private String observation;

    @JsonDeserialize(using = BytesDeserializer.class)
    private byte[] formsFile;

    private Integer decisionId;
    private String decisionLabel;
}
