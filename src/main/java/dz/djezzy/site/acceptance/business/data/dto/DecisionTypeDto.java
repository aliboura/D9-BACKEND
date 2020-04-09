package dz.djezzy.site.acceptance.business.data.dto;

import dz.djezzy.site.acceptance.business.data.entities.Decision;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DecisionTypeDto implements Serializable {

    private Integer id;
    private String label;
    private List<DecisionDto> decisionList = new ArrayList<>();
}
