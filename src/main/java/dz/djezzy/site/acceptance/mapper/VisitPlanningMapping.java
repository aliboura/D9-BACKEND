package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VisitPlanningMapping extends GenericMapper<VisitPlanning, VisitPlanningDto> {

    VisitPlanningMapping INSTANCE = Mappers.getMapper(VisitPlanningMapping.class);

    @Mappings({
            @Mapping(source = "site.id", target = "siteId"),
            @Mapping(source = "site.codeSite", target = "siteCode"),
            @Mapping(source = "site.nomSite", target = "siteName"),
            @Mapping(source = "site.dateD1", target = "dateD1"),
            @Mapping(source = "site.regionId", target = "regionId"),
            @Mapping(source = "site.wilaya.label", target = "wilayaLabel"),
            @Mapping(source = "site.typeSite.id", target = "typeSiteId"),
            @Mapping(source = "site.audited", target = "audited"),
            @Mapping(expression = "java(source.isFirstVisit())", target = "firstVisit"),
            @Mapping(expression = "java(source.isSecondVisit())", target = "secondVisit")
    })
    @Override
    VisitPlanningDto toDto(VisitPlanning source);

    @Mappings({
            @Mapping(target = "site.id", source = "siteId"),
            @Mapping(target = "site.codeSite", source = "siteCode"),
            @Mapping(target = "site.nomSite", source = "siteName"),
            @Mapping(target = "site.dateD1", source = "dateD1"),
            @Mapping(target = "site.regionId", source = "regionId"),
            @Mapping(target = "site.wilaya.label", source = "wilayaLabel"),
            @Mapping(target = "site.typeSite.id", source = "typeSiteId"),
            @Mapping(target = "site.audited", source = "audited")
    })
    @Override
    VisitPlanning toModel(VisitPlanningDto target);
}
