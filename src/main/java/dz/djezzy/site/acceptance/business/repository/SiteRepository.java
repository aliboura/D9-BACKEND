package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long>, QuerydslPredicateExecutor<Site>, JpaSpecificationExecutor<Site> {

    Site findByCodeSite(String codeSite);

    @Query(value = "SELECT s FROM Site s where s.audited = ?1 and s.wilaya.id IN ?2")
    Page<Site> findBySites(Boolean audited, List<Integer> cities, Pageable pageable);


    @Query(value = "SELECT s FROM Site s where s.audited = ?1 " +
            "and (lower(s.codeSite) like ?2 or lower(s.wilaya.label) like ?2) " +
            "and s.wilaya.id IN ?3 order by s.dateD1 desc")
    Page<Site> findByLikeCodeSite(Boolean audited, String codeSite, List<Integer> cities, Pageable pageable);

    @Query(value = "SELECT s FROM Site s, VisitPlanning p " +
            "where s.id = p.site.id " +
            "and s.audited = false " +
            "and s.codeSite = ?1 " +
            "and p.engineerSiteV1 = ?2 ")
    Page<Site> findSites(String codeSite, String username, Pageable pageable);


    @Query(value = "SELECT s FROM Site s, VisitPlanning p " +
            "where s.id = p.site.id " +
            "and s.audited = false " +
            "and s.codeSite = ?1 " +
            "and p.engineerSiteV1 = ?3 " +
            "and s.wilaya.id IN ?2")
    Page<Site> findSites(String codeSite, List<Integer> wilayas, String username, Pageable pageable);

    @Query(value = "SELECT s FROM Site s, VisitPlanning  p " +
            "where s.id = p.site.id " +
            "and p.engineerSiteV1 = ?1 " +
            "and s.audited = false")
    Page<Site> findSitesByUserWilayas(String username, Pageable pageable);

    @Query(value = "SELECT s FROM Site s, VisitPlanning p " +
            "where s.id = p.site.id " +
            "and s.audited = ?1 " +
            "and p.engineerSiteV1 = ?3 " +
            "and s.wilaya.id IN ?2")
    Page<Site> findByCitiesUserV1(Boolean audited, List<Integer> cities, String username, Pageable pageable);
}
