package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusAuditSiteRepository extends JpaRepository<StatusAuditSite, Integer>, QuerydslPredicateExecutor<StatusAuditSite>, JpaSpecificationExecutor<StatusAuditSite> {
}
