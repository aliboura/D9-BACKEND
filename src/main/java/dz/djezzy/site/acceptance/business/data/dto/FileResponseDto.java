package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dz.djezzy.site.acceptance.tools.BytesDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileResponseDto implements Serializable {

    @JsonDeserialize(using = BytesDeserializer.class)
    private byte[] file;
}
