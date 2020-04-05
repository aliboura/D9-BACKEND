package dz.djezzy.site.acceptance.business.data.dto;

import dz.djezzy.site.acceptance.business.data.entities.Decision;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoriesDto implements Serializable {

    private int id;
    private String label;
    private int position;
    private Integer valueType;
    private boolean status;
    private boolean blocking;
    private int categoriesId;
    private String categoriesLabel;
    private List<DecisionDto> decisionsList = new ArrayList<>();
}
