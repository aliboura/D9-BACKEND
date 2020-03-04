package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.WilayaDto;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import dz.djezzy.site.acceptance.business.repository.WilayaRepository;
import dz.djezzy.site.acceptance.business.services.WilayaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WilayaServiceIMPL extends GenericServiceImpl<WilayaRepository, Wilaya, WilayaDto, Integer>
        implements WilayaService {
}
