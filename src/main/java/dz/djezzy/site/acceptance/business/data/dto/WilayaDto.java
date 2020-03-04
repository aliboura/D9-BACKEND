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

    public WilayaDto(int id, String label, String regionId) {
        this.id = id;
        this.label = label;
        this.regionId = regionId;
    }

    public WilayaDto(String label, String regionId) {
        this.label = label;
        this.regionId = regionId;
    }
}
