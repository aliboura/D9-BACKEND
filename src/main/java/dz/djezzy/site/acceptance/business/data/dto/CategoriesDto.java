package dz.djezzy.site.acceptance.business.data.dto;

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
@AllArgsConstructor
public class CategoriesDto implements Serializable {

    private Long id;
    private String label;
    private List<SubCategoriesDto> subCategoriesDtoList = new ArrayList<>();

    public CategoriesDto(String label) {
        this.label = label;
    }
}
