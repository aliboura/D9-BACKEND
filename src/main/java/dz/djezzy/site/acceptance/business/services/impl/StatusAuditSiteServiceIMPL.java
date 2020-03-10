package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import dz.djezzy.site.acceptance.business.repository.StatusAuditSiteRepository;
import dz.djezzy.site.acceptance.business.services.StatusAuditSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatusAuditSiteServiceIMPL extends GenericServiceImpl<StatusAuditSiteRepository, StatusAuditSite, StatusAuditSiteDto, Integer>
        implements StatusAuditSiteService {
}
