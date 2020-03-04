package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.Status;
import dz.djezzy.site.acceptance.business.repository.StatusRepository;
import dz.djezzy.site.acceptance.business.services.StatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatusServiceIMPL extends GenericServiceImpl<StatusRepository, Status, StatusDto, Long>
        implements StatusService {
}
