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

    @Query(value = "SELECT s FROM Site s where s.audited = ?1 and lower(s.codeSite) Like ?2 and s.wilaya.id IN ?3")
    Page<Site> findByLikeCodeSite(Boolean audited, String codeSite, List<Integer> cities, Pageable pageable);

    @Query(value = "SELECT s FROM Site s where s.audited = false and s.codeSite = ?1 and (s.userV1 = ?2 or s.userV2 = ?2)")
    Page<Site> findSites(String codeSite, String userV1, Pageable pageable);


    @Query(value = "SELECT s FROM Site s where s.audited = false and s.codeSite = ?1 and s.wilaya.id IN ?2 and (s.userV1 = ?3 or s.userV2 = ?3)")
    Page<Site> findSites(String codeSite, List<Integer> wilayas, String userV1, Pageable pageable);

    @Query(value = "SELECT s FROM Site s where s.audited = false and s.wilaya.id IN ?1 and (s.userV1 = ?2 or s.userV2 = ?2)")
    Page<Site> findSitesByUserWilayas(List<Integer> wilayas, String userV1, Pageable pageable);
}
