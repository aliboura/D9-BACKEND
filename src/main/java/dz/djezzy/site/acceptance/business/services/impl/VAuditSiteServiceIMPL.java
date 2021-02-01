package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.VAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.VAuditSite;
import dz.djezzy.site.acceptance.business.repository.VAuditSiteRepository;
import dz.djezzy.site.acceptance.business.services.VAuditSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VAuditSiteServiceIMPL extends GenericServiceImpl<VAuditSiteRepository, VAuditSite, VAuditSiteDto, Long> implements VAuditSiteService {
}
