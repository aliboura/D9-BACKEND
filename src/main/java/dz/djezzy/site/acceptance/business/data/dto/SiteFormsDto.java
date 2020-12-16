package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dz.djezzy.site.acceptance.tools.BytesDeserializer;
import dz.djezzy.site.acceptance.tools.BytesSerializer;
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
    @JsonSerialize(using = BytesSerializer.class)
    private byte[] formsFile;

    private Integer decisionId;
    private String decisionLabel;
}
