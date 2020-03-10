package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditSiteRepository extends JpaRepository<AuditSite, Integer> {
}
