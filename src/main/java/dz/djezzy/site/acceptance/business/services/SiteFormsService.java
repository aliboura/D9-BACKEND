package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.SiteFormsDto;
import dz.djezzy.site.acceptance.business.data.entities.SiteForms;

import java.util.List;

public interface SiteFormsService extends GenericService<SiteForms, SiteFormsDto, Integer> {

    List<SiteFormsDto> findByCodeSite(String codeSite);
}
