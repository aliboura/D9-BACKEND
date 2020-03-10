package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
    private int position;
    private char status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<SubCategoriesDto> listSubCategories = new ArrayList<>();

    public CategoriesDto(String label) {
        this.label = label;
    }

    public CategoriesDto(String label, int position, char status) {
        this.label = label;
        this.position = position;
        this.status = status;
    }
}
