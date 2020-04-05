package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AuditSiteLineMapping.class, CategoriesMapping.class, StatusAuditSiteMapping.class})
public interface AuditSiteMapping extends GenericMapper<AuditSite, AuditSiteDto> {

    AuditSiteMapping INSTANCE = Mappers.getMapper(AuditSiteMapping.class);

    @Mappings({
            @Mapping(source = "typeAuditSite.id", target = "typeAuditSiteId"),
            @Mapping(source = "typeAuditSite.label", target = "typeAuditSiteLabel"),
            @Mapping(source = "currentSatus.id", target = "currentSatusId"),
            @Mapping(source = "currentSatus.label", target = "currentSatusLabel"),
            @Mapping(source = "currentSatus.styleCSS", target = "currentSatusStyleCSS"),
            @Mapping(source = "currentCategories.id", target = "currentCategoriesId"),
            @Mapping(source = "currentCategories.label", target = "currentCategoriesLabel"),
            @Mapping(source = "currentCategories", target = "currentCategory"),
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
            @Mapping(target = "typeAuditSite.id", source = "typeAuditSiteId"),
            @Mapping(target = "typeAuditSite.label", source = "typeAuditSiteLabel"),
            @Mapping(target = "currentSatus.id", source = "currentSatusId"),
            @Mapping(target = "currentSatus.label", source = "currentSatusLabel"),
            @Mapping(target = "currentSatus.styleCSS", source = "currentSatusStyleCSS"),
            @Mapping(target = "currentCategories.id", source = "currentCategoriesId"),
            @Mapping(target = "currentCategories.label", source = "currentCategoriesLabel"),
            @Mapping(target = "currentCategories", source = "currentCategory"),
            @Mapping(target = "firstDecision.id", source = "firstDecisionId"),
            @Mapping(target = "firstDecision.label", source = "firstDecisionLabel"),
            @Mapping(target = "secondDecision.id", source = "secondDecisionId"),
            @Mapping(target = "secondDecision.label", source = "secondDecisionLabel"),
            @Mapping(target = "auditLineList", source = "auditSiteLineDtoList"),
            @Mapping(target = "statusAuditSitesList", source = "statusAuditSitesDtoList")
    })
    @Override
    AuditSite toModel(AuditSiteDto target);

    @AfterMapping
    default AuditSite doAfterMapping(@MappingTarget AuditSite entity) {
        if (entity != null && entity.getFirstDecision().getId() == null) {
            entity.setFirstDecision(null);
        }
        if (entity != null && entity.getSecondDecision().getId() == null) {
            entity.setSecondDecision(null);
        }
        if (entity != null && entity.getTypeAuditSite().getId() == null) {
            entity.setTypeAuditSite(null);
        }
        return entity;
    }

}
