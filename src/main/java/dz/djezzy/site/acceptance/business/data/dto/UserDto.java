package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dz.djezzy.site.acceptance.tools.BytesDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String matricule;
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String regionId;
    @JsonDeserialize(using = BytesDeserializer.class)
    private byte[] imgProfile;
    private Boolean enabled;
    private Boolean expired;
    private Boolean credentials;
    private Boolean locked;

    private Set<RoleDto> roleSet = new HashSet<>();
    private Set<WilayaRegionDto> wilayaSet = new HashSet<>();

    @JsonDeserialize(using = BytesDeserializer.class)
    public Boolean isSiteEngineer(Set<RoleDto> roleSet) {
        RoleDto roleSiteEngineer = roleSet.stream().filter(x -> x.getLabel().equals("ENGINEER_SITE")).findAny().orElse(null);
        return roleSiteEngineer != null;
    }

    @JsonDeserialize(using = BytesDeserializer.class)
    public Boolean isOMEngineer(Set<RoleDto> roleSet) {
        RoleDto roleOMEngineer = roleSet.stream().filter(x -> x.getLabel().equals("ENGINEER_OM")).findAny().orElse(null);
        return roleOMEngineer != null;
    }
}
