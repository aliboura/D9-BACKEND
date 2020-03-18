package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.WilayaDto;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;

import java.util.List;

public interface WilayaService extends GenericService<Wilaya, WilayaDto, Integer> {

    List<WilayaDto> findByRegionId(String regionId);
}
