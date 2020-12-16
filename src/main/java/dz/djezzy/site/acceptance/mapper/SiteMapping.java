package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import dz.djezzy.site.acceptance.tools.SiteUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface SiteMapping extends GenericMapper<Site, SiteDto> {

    SiteMapping INSTANCE = Mappers.getMapper(SiteMapping.class);

    @Mappings({
            @Mapping(source = "typeSite.id", target = "typeSiteId"),
            @Mapping(source = "typeSite.typeSiteLib", target = "typeSiteLib"),
            @Mapping(source = "wilaya.id", target = "wilayaId"),
            @Mapping(source = "wilaya.label", target = "wilayaLabel"),
            @Mapping(expression = "java(SiteMapping.getUserV1(source))", target = "userV1"),
            @Mapping(expression = "java(SiteMapping.getUserDateV1(source))", target = "userV1Date"),
            @Mapping(expression = "java(SiteMapping.getUserV2(source))", target = "userV2"),
            @Mapping(expression = "java(SiteMapping.getUserDateV2(source))", target = "userV2Date"),
            @Mapping(expression = "java(SiteMapping.getUserOMV1(source))", target = "userOMV1"),
            @Mapping(expression = "java(SiteMapping.getUserOMV2(source))", target = "userOMV2"),
            @Mapping(expression = "java(SiteMapping.getUserOMDateV1(source))", target = "userOMV1Date"),
            @Mapping(expression = "java(SiteMapping.getUserOMDateV2(source))", target = "userOMV2Date")
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


    static String getUserV1(Site source) {
        return SiteUtils.getUserV1(source);
    }

    static String getUserOMV1(Site source) {
        return SiteUtils.getUserOMV1(source);
    }


    static String getUserV2(Site source) {
        return SiteUtils.getUserV2(source);
    }

    static String getUserOMV2(Site source) {
        return SiteUtils.getUserOMV2(source);
    }

    static Date getUserDateV1(Site source) {
        return SiteUtils.getUserDateV1(source);
    }

    static Date getUserDateV2(Site source) {
        return SiteUtils.getUserDateV2(source);
    }

    static Date getUserOMDateV1(Site source) {
        return SiteUtils.getUserOMDateV1(source);
    }

    static Date getUserOMDateV2(Site source) {
        return SiteUtils.getUserOMDateV2(source);
    }

}
