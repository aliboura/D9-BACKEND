package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface AuditSiteRepository extends JpaRepository<AuditSite, Integer>, QuerydslPredicateExecutor<AuditSite>, JpaSpecificationExecutor<AuditSite> {

    @Query(value = "select a from AuditSite a, Site s, VisitPlanning  p" +
            " where a.site.id = s.id and s.id = p.site.id" +
            " and (p.engineerSiteV1 = ?1 or p.engineerSiteV2 = ?1 or p.engineerOMV1 = ?1 or p.engineerOMV2 = ?1)")
    Page<AuditSite> findByEngineerSite(String username, Pageable pageable);

    @Query(value = "select a from AuditSite a, Site s, VisitPlanning  p" +
            " where a.site.id = s.id and s.id = p.site.id" +
            " and (p.engineerSiteV1 = ?1 or p.engineerSiteV2 = ?1 or p.engineerOMV1 = ?1 or p.engineerOMV2 = ?1)")
    List<AuditSite> findByEngineerSite(String username);

    @Query(value = "select a from AuditSite a, Site s, VisitPlanning  p" +
            " where a.site.id = s.id and s.id = p.site.id" +
            " and a.currentSatus.id = ?2" +
            " and (p.engineerSiteV1 = ?1 or p.engineerSiteV2 = ?1)")
    List<AuditSite> findByEngineerSite(String username, Integer statusId);

    @Query(value = "select a from AuditSite a, Site s, VisitPlanning  p" +
            " where a.site.id = s.id and s.id = p.site.id" +
            " and a.currentSatus.id = ?2" +
            " and a.auditDate between ?3 and ?4" +
            " and (p.engineerSiteV1 = ?1 or p.engineerSiteV2 = ?1)")
    List<AuditSite> findByEngineerSite(String username, Integer statusId, Date fromDate, Date toDate);

}
