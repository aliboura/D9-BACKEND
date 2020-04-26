package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.SiteForms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface SiteFormsRepository extends JpaRepository<SiteForms, Integer>, QuerydslPredicateExecutor<SiteForms>, JpaSpecificationExecutor<SiteForms> {

    List<SiteForms> findByCodeSite(String codeSite);
}
