package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.WilayaRegionDto;
import dz.djezzy.site.acceptance.business.data.entities.WilayaRegion;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WilayaRegionMapping extends GenericMapper<WilayaRegion, WilayaRegionDto> {

    WilayaRegionMapping INSTANCE = Mappers.getMapper(WilayaRegionMapping.class);
}
