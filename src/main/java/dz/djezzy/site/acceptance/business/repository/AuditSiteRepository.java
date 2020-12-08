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
            " and (lower(p.engineerSiteV1) = lower(?1) or lower(p.engineerSiteV2) = lower(?1) " +
            "or lower(p.engineerOMV1) = lower(?1) or lower(p.engineerOMV2) = lower(?1))")
    Page<AuditSite> findByEngineerSite(String username, Pageable pageable);

    @Query(value = "select a from AuditSite a, Site s, VisitPlanning  p" +
            " where a.site.id = s.id and s.id = p.site.id" +
            " and (lower(p.engineerSiteV1) = lower(?1) or lower(p.engineerSiteV2) = lower(?1) " +
            "or lower(p.engineerOMV1) = lower(?1) or lower(p.engineerOMV2) = lower(?1))")
    List<AuditSite> findByEngineerSite(String username);

    @Query(value = "select a from AuditSite a, Site s, VisitPlanning  p" +
            " where a.site.id = s.id and s.id = p.site.id" +
            " and a.currentSatus.id = ?2" +
            " and (lower(p.engineerSiteV1) = lower(?1) or lower(p.engineerSiteV2) = lower(?1))")
    List<AuditSite> findByEngineerSite(String username, Integer statusId);

    @Query(value = "select a from AuditSite a, Site s, VisitPlanning  p" +
            " where a.site.id = s.id and s.id = p.site.id" +
            " and a.currentSatus.id = ?2" +
            " and a.auditDate between ?3 and ?4" +
            " and (lower(p.engineerSiteV1) = lower(?1) or lower(p.engineerSiteV2) = lower(?1))")
    List<AuditSite> findByEngineerSite(String username, Integer statusId, Date fromDate, Date toDate);

}
