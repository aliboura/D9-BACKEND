package dz.djezzy.site.acceptance.business.services.impl;


import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.business.repository.SiteRepository;
import dz.djezzy.site.acceptance.business.services.SiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SiteServiceIMPL extends GenericServiceImpl<SiteRepository, Site, SiteDto, Integer>
        implements SiteService {
}
