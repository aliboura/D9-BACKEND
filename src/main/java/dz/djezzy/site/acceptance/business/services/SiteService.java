package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;

public interface SiteService extends GenericService<Site, SiteDto, Long> {

    SiteDto findByCodeSite(String codeSite);
}
