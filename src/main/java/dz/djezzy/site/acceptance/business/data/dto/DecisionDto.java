package dz.djezzy.site.acceptance.business.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DecisionDto implements Serializable {

    private Integer id;
    private String label;
    private Integer position;
    private Integer typeValue;
    private Boolean status;
    private Boolean closed;

}
