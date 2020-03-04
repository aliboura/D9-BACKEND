package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommuneDto implements Serializable {

    private int id;
    private String label;

    private int wilayaId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String wilayaLabel;

    public CommuneDto(String label) {
        this.label = label;
    }
}
