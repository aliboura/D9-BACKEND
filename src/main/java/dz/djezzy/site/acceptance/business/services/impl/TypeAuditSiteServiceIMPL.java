package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.TypeAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.TypeAuditSite;
import dz.djezzy.site.acceptance.business.repository.TypeAuditSiteRepository;
import dz.djezzy.site.acceptance.business.services.TypeAuditSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeAuditSiteServiceIMPL extends GenericServiceImpl<TypeAuditSiteRepository, TypeAuditSite, TypeAuditSiteDto, Integer>
        implements TypeAuditSiteService {
}
