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
public class TypeSiteDto implements Serializable {

    private String id;
    private String typeDetailEquip;
    private String typeInstallation;
    private String typeSiteLib;

    public TypeSiteDto(String typeDetailEquip, String typeInstallation, String typeSiteLib) {
        this.typeDetailEquip = typeDetailEquip;
        this.typeInstallation = typeInstallation;
        this.typeSiteLib = typeSiteLib;
    }
}
