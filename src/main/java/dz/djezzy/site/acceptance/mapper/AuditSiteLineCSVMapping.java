package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineCSV;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DecisionMapping.class})
public interface AuditSiteLineCSVMapping extends GenericMapper<AuditSiteLine, AuditSiteLineCSV> {

    AuditSiteLineCSVMapping INSTANCE = Mappers.getMapper(AuditSiteLineCSVMapping.class);

    @Mappings({
            @Mapping(source = "auditSite.id", target = "auditSiteId"),
            @Mapping(source = "categories.id", target = "categoriesId"),
            @Mapping(source = "categories.label", target = "categoriesLabel"),
            @Mapping(source = "subCategories.id", target = "subCategoriesId"),
            @Mapping(source = "firstDecision.label", target = "firstDecisionLabel")
    })
    @Override
    AuditSiteLineCSV toDto(AuditSiteLine source);

    @Mappings({
            @Mapping(target = "auditSite.id", source = "auditSiteId"),
            @Mapping(target = "categories.id", source = "categoriesId"),
            @Mapping(target = "categories.label", source = "categoriesLabel"),
            @Mapping(target = "subCategories.id", source = "subCategoriesId"),
            @Mapping(target = "firstDecision.label", source = "firstDecisionLabel")
    })
    @Override
    AuditSiteLine toModel(AuditSiteLineCSV target);

    @AfterMapping
    default AuditSiteLine doAfterMapping(@MappingTarget AuditSiteLine entity) {
        if (entity != null && entity.getFirstDecision().getId() == null) {
            entity.setFirstDecision(null);
        }
        if (entity != null && entity.getSecondDecision().getId() == null) {
            entity.setSecondDecision(null);
        }
        return entity;
    }
}
