package dz.djezzy.site.acceptance.business.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoriesDto implements Serializable {

    private int id;
    private String label;
    private int position;
    private boolean status;
    private int categoriesId;
    private String categoriesLabel;

    public SubCategoriesDto(String label) {
        this.label = label;
    }
}
