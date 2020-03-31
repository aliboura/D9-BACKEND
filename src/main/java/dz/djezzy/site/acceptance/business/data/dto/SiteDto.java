package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dz.djezzy.site.acceptance.business.data.entities.TypeSite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteDto implements Serializable {

    private long id;
    private String codeSite;
    private Date dateD1;
    private String nomSite;
    private String numSite;
    private String prioriteRadio;
    private String regionId;
    private String serviceDemandeur;
    private Boolean audited;

    private String typeSiteId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String typeSiteLib;

    private int wilayaId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String wilayaLabel;

}
