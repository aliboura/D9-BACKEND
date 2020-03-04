package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.CommuneDto;
import dz.djezzy.site.acceptance.business.data.entities.Commune;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommunMapping extends GenericMapper<Commune, CommuneDto> {

    CommunMapping INSTANCE = Mappers.getMapper(CommunMapping.class);

    @Mappings({
            @Mapping(source = "wilaya.id", target = "wilayaId"),
            @Mapping(source = "wilaya.label", target = "wilayaLabel")
    })
    @Override
    CommuneDto toDto(Commune source);

    @Mappings({
            @Mapping(target = "wilaya.id", source = "wilayaId"),
            @Mapping(target = "wilaya.label", source = "wilayaLabel")
    })
    @Override
    Commune toModel(CommuneDto target);

}
