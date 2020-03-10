package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.repository.AuditSiteRepository;
import dz.djezzy.site.acceptance.business.services.AuditSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuditSiteServiceIMPL extends GenericServiceImpl<AuditSiteRepository, AuditSite, AuditSiteDto, Integer>
        implements AuditSiteService {
}
