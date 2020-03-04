package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.CommuneDto;
import dz.djezzy.site.acceptance.business.data.entities.Commune;
import dz.djezzy.site.acceptance.business.repository.CommuneRepository;
import dz.djezzy.site.acceptance.business.services.CommuneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommuneServiceIMPL extends GenericServiceImpl<CommuneRepository, Commune, CommuneDto, Integer>
        implements CommuneService {
}
