package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditSiteMapping extends GenericMapper<AuditSite, AuditSiteDto> {

    AuditSiteMapping INSTANCE = Mappers.getMapper(AuditSiteMapping.class);

    @Mappings({
            @Mapping(source = "currentSatus.id", target = "currentSatusId"),
            @Mapping(source = "currentSatus.label", target = "currentSatusLabel"),
            @Mapping(source = "currentCategories.id", target = "currentCategoriesId"),
            @Mapping(source = "currentCategories.label", target = "currentCategoriesLabel"),
            @Mapping(source = "firstDecision.id", target = "firstDecisionId"),
            @Mapping(source = "firstDecision.label", target = "firstDecisionLabel"),
            @Mapping(source = "secondDecision.id", target = "secondDecisionId"),
            @Mapping(source = "secondDecision.label", target = "secondDecisionLabel"),
            @Mapping(source = "auditLineList", target = "auditSiteLineDtoList"),
            @Mapping(source = "statusAuditSitesList", target = "statusAuditSitesDtoList")
    })
    @Override
    AuditSiteDto toDto(AuditSite source);

    @Mappings({
            @Mapping(target = "currentSatus.id", source = "currentSatusId"),
            @Mapping(target = "currentSatus.label", source = "currentSatusLabel"),
            @Mapping(target = "currentCategories.id", source = "currentCategoriesId"),
            @Mapping(target = "currentCategories.label", source = "currentCategoriesLabel"),
            @Mapping(target = "firstDecision.id", source = "firstDecisionId"),
            @Mapping(target = "firstDecision.label", source = "firstDecisionLabel"),
            @Mapping(target = "secondDecision.id", source = "secondDecisionId"),
            @Mapping(target = "secondDecision.label", source = "secondDecisionLabel"),
            @Mapping(target = "auditLineList", source = "auditSiteLineDtoList"),
            @Mapping(target = "statusAuditSitesList", source = "statusAuditSitesDtoList")
    })
    @Override
    AuditSite toModel(AuditSiteDto target);

}
