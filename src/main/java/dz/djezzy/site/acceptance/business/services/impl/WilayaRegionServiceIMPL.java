package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.WilayaRegionDto;
import dz.djezzy.site.acceptance.business.data.entities.WilayaRegion;
import dz.djezzy.site.acceptance.business.repository.WilayaRegionRepository;
import dz.djezzy.site.acceptance.business.services.WilayaRegionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class WilayaRegionServiceIMPL extends GenericServiceImpl<WilayaRegionRepository, WilayaRegion, WilayaRegionDto, Integer>
        implements WilayaRegionService {

    private final WilayaRegionRepository wilayaRegionRepository;

    @Override
    public List<WilayaRegionDto> findByRegionId(String regionId) {
        return toDto(wilayaRegionRepository.findByRegionId(regionId));
    }
}
