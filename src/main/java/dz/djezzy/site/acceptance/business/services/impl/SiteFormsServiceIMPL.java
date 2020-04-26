package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.SiteFormsDto;
import dz.djezzy.site.acceptance.business.data.entities.SiteForms;
import dz.djezzy.site.acceptance.business.repository.SiteFormsRepository;
import dz.djezzy.site.acceptance.business.services.SiteFormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SiteFormsServiceIMPL extends GenericServiceImpl<SiteFormsRepository, SiteForms, SiteFormsDto, Integer>
        implements SiteFormsService {

    @Autowired
    private SiteFormsRepository siteFormsRepository;

    @Override
    public List<SiteFormsDto> findByCodeSite(String codeSite) {
        return asDto(siteFormsRepository.findByCodeSite(codeSite));
    }
}
