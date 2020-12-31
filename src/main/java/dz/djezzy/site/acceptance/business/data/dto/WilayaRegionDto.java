package dz.djezzy.site.acceptance.business.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WilayaRegionDto implements Serializable {

    private int id;
    private String label;
    private String regionId;

    private Integer groupeId;
    private String groupeMails;
    private List<String> groupSites = new ArrayList<>();
    private List<String> groupOMs = new ArrayList<>();
}
