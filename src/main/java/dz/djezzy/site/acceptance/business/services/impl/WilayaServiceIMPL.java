package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.WilayaDto;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import dz.djezzy.site.acceptance.business.repository.WilayaRepository;
import dz.djezzy.site.acceptance.business.services.WilayaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WilayaServiceIMPL extends GenericServiceImpl<WilayaRepository, Wilaya, WilayaDto, Integer>
        implements WilayaService {

    @Autowired
    private WilayaRepository wilayaRepository;

    @Override
    public List<WilayaDto> findByRegionId(String regionId) {
        return toDto(wilayaRepository.findByRegionId(regionId));
    }
}
