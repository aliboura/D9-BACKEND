package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.TypeAuditSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TypeAuditSiteRepository extends JpaRepository<TypeAuditSite, Integer>, QuerydslPredicateExecutor<TypeAuditSite>, JpaSpecificationExecutor<TypeAuditSite> {
}
