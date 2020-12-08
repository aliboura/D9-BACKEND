package dz.djezzy.site.acceptance.business.services.impl;


import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.business.repository.SiteRepository;
import dz.djezzy.site.acceptance.business.services.SiteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class SiteServiceIMPL extends GenericServiceImpl<SiteRepository, Site, SiteDto, Long>
        implements SiteService {

    private final SiteRepository siteRepository;

    @Override
    public SiteDto findByCodeSite(String codeSite) {
        return asDto(siteRepository.findByCodeSite(codeSite));
    }

    @Override
    public Page<SiteDto> findBySites(Boolean audited, List<Integer> cities, Pageable pageable) {
        return siteRepository.findBySites(audited, cities, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<SiteDto> findByLikeCodeSite(Boolean audited, String codeSite, List<Integer> cities, Pageable pageable) {
        return siteRepository.findByLikeCodeSite(audited, codeSite, cities, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<SiteDto> findSites(String codeSite, String username, Pageable pageable) {
        return siteRepository.findSites(codeSite, username, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<SiteDto> findSites(String codeSite, List<Integer> wilayas, String username, Pageable pageable) {
        return siteRepository.findSites(codeSite, wilayas, username, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<SiteDto> findSitesByUserWilayas(String username, Pageable pageable) {
        return siteRepository.findSitesByUserWilayas(username, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<SiteDto> findByCitiesUserV1(Boolean audited, List<Integer> cities, String username, Pageable pageable) {
        return siteRepository.findByCitiesUserV1(audited, cities, username, pageable).map(data -> asDto(data));
    }
}
