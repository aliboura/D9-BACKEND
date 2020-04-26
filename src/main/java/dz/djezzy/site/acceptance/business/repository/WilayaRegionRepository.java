package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import dz.djezzy.site.acceptance.business.data.entities.WilayaRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface WilayaRegionRepository extends JpaRepository<WilayaRegion, Integer>, QuerydslPredicateExecutor<WilayaRegion>, JpaSpecificationExecutor<WilayaRegion> {

    List<WilayaRegion> findByRegionId(String regionId);

}
