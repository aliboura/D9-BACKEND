package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CategoriesDto implements Serializable {

    private Integer id;
    private String label;
    private Integer position;
    private char status;
    private Boolean first;
    private Boolean last;

    private Integer nextCatId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String nextCatLabel;
    private Integer previousCatId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String previousCatLabel;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<SubCategoriesDto> listSubCategories;
}
