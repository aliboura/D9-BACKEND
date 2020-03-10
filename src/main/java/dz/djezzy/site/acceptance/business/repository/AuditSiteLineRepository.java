package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditSiteLineRepository extends JpaRepository<AuditSiteLine, Integer> {
}
