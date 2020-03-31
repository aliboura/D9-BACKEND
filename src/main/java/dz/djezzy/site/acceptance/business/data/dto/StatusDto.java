package dz.djezzy.site.acceptance.business.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto implements Serializable {

    private Integer id;
    private String label;
    private String description;
    private String styleCSS;
    private boolean motif;
    private boolean first;

    public StatusDto(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
}
