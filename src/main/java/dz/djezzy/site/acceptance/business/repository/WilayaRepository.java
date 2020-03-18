package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WilayaRepository extends JpaRepository<Wilaya, Integer>, QuerydslPredicateExecutor<Wilaya>, JpaSpecificationExecutor<Wilaya> {

    List<Wilaya> findByRegionId(String regionId);
}
