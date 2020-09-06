package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

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
    private Boolean status;
    private Boolean first;
    private Boolean last;
    private Integer orderNum;

    private Integer typeAuditSiteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String typeAuditSiteLabel;

    private Integer nextCatId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String nextCatLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer nextCatOrder;

    private Integer previousCatId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String previousCatLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer previousCatOrder;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<SubCategoriesDto> listSubCategories = new ArrayList<>();
}
