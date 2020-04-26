package dz.djezzy.site.acceptance.tools;

import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppsUtils {

    public static Collection<GrantedAuthority> getAuthoritiesFromList(Collection<RoleDto> roles) {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" + role.getLabel())).collect(Collectors.toList());
    }

    public static Collection<GrantedAuthority> getAuthoritiesFromMaps(List<Map<String, String>> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toList());
    }
}
