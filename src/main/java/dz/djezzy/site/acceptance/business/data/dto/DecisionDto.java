package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.io.Serializable;

@Getter
@Setter
public class DecisionDto implements Serializable {

    private Integer id;
    private String label;
    private Integer position;
    private Integer decisionTypeId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String decisionTypeLabel;
    private Boolean status;
    private Boolean closed;

}
