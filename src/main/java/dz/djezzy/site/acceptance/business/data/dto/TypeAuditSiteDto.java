package dz.djezzy.site.acceptance.business.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeAuditSiteDto {

    private Integer id;
    private String label;
    private String description;
    private Boolean status;
}
