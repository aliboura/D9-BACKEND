package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.VAuditSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface VAuditSiteRepository extends JpaRepository<VAuditSite, Long>, QuerydslPredicateExecutor<VAuditSite>, JpaSpecificationExecutor<VAuditSite> {
}
