package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.TypeSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.TypeSite;
import dz.djezzy.site.acceptance.business.repository.TypeSiteRepository;
import dz.djezzy.site.acceptance.business.services.TypeSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeSiteServiceIMPL extends GenericServiceImpl<TypeSiteRepository, TypeSite, TypeSiteDto, String>
        implements TypeSiteService {
}
