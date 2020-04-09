package dz.djezzy.site.acceptance.business.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class WilayaDto implements Serializable {

    private int id;
    private String label;
    private String regionId;

}
