package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitPlanningRepository extends JpaRepository<VisitPlanning, Long>, QuerydslPredicateExecutor<VisitPlanning>, JpaSpecificationExecutor<VisitPlanning> {


    @Query(value = "select p from VisitPlanning p where p.site.wilaya.id IN ?1")
    Page<VisitPlanning> findByCities(List<Integer> citiesIds, Pageable pageable);

    @Query(value = "select p from VisitPlanning p where p.site.wilaya.id IN ?2 and (p.site.codeSite = ?1 or p.site.nomSite = ?1)")
    Page<VisitPlanning> findByCode(String code, List<Integer> citiesIds, Pageable pageable);

    @Query(value = "select p from VisitPlanning p where p.engineerSiteV1 = ?1 and p.site.wilaya.id IN ?4 and p.engineerSiteDateV1 between ?2 and ?3")
    Page<VisitPlanning> findFirstVisitPlannings(String engineerSiteV1, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable);

    @Query(value = "select p from VisitPlanning p where p.engineerSiteV2 = ?1 and p.site.wilaya.id IN ?4 and p.engineerSiteDateV2 between ?2 and ?3")
    Page<VisitPlanning> findSecondVisitPlannings(String engineerSiteV2, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable);

}
