package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SiteService extends GenericService<Site, SiteDto, Long> {

    SiteDto findByCodeSite(String codeSite);

    Page<SiteDto> findBySites(Boolean audited, List<Integer> cities, Pageable pageable);

    Page<SiteDto> findByLikeCodeSite(Boolean audited, String codeSite, List<Integer> cities, Pageable pageable);

    Page<SiteDto> findSites(String codeSite, String username, Pageable pageable);

    Page<SiteDto> findSites(String codeSite, List<Integer> wilayas, String username, Pageable pageable);

    Page<SiteDto> findSitesByUserWilayas(String username, Pageable pageable);

}
