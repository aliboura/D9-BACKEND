package dz.djezzy.site.acceptance.business.data.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String name = "SYSTEM";
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        name = (String) auth.getPrincipal();
        return Optional.ofNullable(name);
    }
}
