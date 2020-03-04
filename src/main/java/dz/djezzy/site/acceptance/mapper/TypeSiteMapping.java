package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.TypeSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.TypeSite;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TypeSiteMapping extends GenericMapper<TypeSite, TypeSiteDto> {

    TypeSiteMapping INSTANCE = Mappers.getMapper(TypeSiteMapping.class);

    @Override
    TypeSiteDto toDto(TypeSite source);

    @Override
    TypeSite toModel(TypeSiteDto target);

}
