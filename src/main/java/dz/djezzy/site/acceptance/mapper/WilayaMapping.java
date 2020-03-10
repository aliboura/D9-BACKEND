package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.WilayaDto;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WilayaMapping extends GenericMapper<Wilaya, WilayaDto> {

    WilayaMapping INSTANCE = Mappers.getMapper(WilayaMapping.class);

    @Override
    WilayaDto toDto(Wilaya source);

    @Override
    Wilaya toModel(WilayaDto target);

}
