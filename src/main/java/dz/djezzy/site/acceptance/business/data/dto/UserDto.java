package dz.djezzy.site.acceptance.business.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String firstName;
    private String lastName;
    private String email;
    private String regionId;
    @JsonDeserialize(using = BytesDeserializer.class)
    private byte[] imgProfile;
    private boolean enabled;
    private boolean expired;
    private boolean credentials;
    private boolean locked;

    private Set<RoleDto> roleSet = new HashSet<>();
    private Set<WilayaRegionDto> wilayaSet = new HashSet<>();
}
