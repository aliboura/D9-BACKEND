package dz.djezzy.site.acceptance.business.data.audit;

import dz.djezzy.site.acceptance.tools.AppsUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String name = AppsUtils.getUserPrincipal();
        return Optional.ofNullable(name);
    }
}
