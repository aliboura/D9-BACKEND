package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SiteMapping extends GenericMapper<Site, SiteDto> {

    SiteMapping INSTANCE = Mappers.getMapper(SiteMapping.class);

    @Mappings({
            @Mapping(source = "typeSite.id", target = "typeSiteId"),
            @Mapping(source = "typeSite.typeSiteLib", target = "typeSiteLib"),
            @Mapping(source = "wilaya.id", target = "wilayaId"),
            @Mapping(source = "wilaya.label", target = "wilayaLabel")
    })
    @Override
    SiteDto toDto(Site source);

    @Mappings({
            @Mapping(target = "typeSite.id", source = "typeSiteId"),
            @Mapping(target = "typeSite.typeSiteLib", source = "typeSiteLib"),
            @Mapping(target = "wilaya.id", source = "wilayaId"),
            @Mapping(target = "wilaya.label", source = "wilayaLabel")
    })
    @Override
    Site toModel(SiteDto target);


}
