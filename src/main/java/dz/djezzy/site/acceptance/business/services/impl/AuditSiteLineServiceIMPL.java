package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.business.repository.AuditSiteLineRepository;
import dz.djezzy.site.acceptance.business.services.AuditSiteLineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuditSiteLineServiceIMPL extends GenericServiceImpl<AuditSiteLineRepository, AuditSiteLine, AuditSiteLineDto, Integer>
        implements AuditSiteLineService {
}
