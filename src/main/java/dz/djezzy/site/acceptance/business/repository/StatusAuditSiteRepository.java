package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusAuditSiteRepository extends JpaRepository<StatusAuditSite, Long> {
}
