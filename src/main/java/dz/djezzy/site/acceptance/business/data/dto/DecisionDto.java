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
public class DecisionDto implements Serializable {

    private int id;
    private String label;
    private int position;
    private boolean status;
    private boolean closed;

    public DecisionDto(String label, int position, boolean status, boolean closed) {
        this.label = label;
        this.position = position;
        this.status = status;
        this.closed = closed;
    }

    public DecisionDto(String label) {
        this.label = label;
    }
}
