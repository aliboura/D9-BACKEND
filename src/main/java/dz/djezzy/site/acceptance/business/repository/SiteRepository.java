package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer>, QuerydslPredicateExecutor<Site>, JpaSpecificationExecutor<Site> {


}
