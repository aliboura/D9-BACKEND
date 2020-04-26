package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.WilayaRegionDto;
import dz.djezzy.site.acceptance.business.data.entities.WilayaRegion;

import java.util.List;

public interface WilayaRegionService extends GenericService<WilayaRegion, WilayaRegionDto, Integer> {

    List<WilayaRegionDto> findByRegionId(String regionId);
}
