package dz.djezzy.site.acceptance.business.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto implements Serializable {
    private Long id;
    private String label;
}
