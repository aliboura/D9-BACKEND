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

    private int id;
    private String label;
    private String description;
    private String styleCSS;
    private boolean motif;
    private boolean first;

    public StatusDto(String label, String description, String styleCSS, boolean motif) {
        this.label = label;
        this.description = description;
        this.styleCSS = styleCSS;
        this.motif = motif;
    }
}
