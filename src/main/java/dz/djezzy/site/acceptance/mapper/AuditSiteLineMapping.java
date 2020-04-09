package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = {DecisionMapping.class})
public interface AuditSiteLineMapping extends GenericMapper<AuditSiteLine, AuditSiteLineDto> {

    AuditSiteLineMapping INSTANCE = Mappers.getMapper(AuditSiteLineMapping.class);

    @Mappings({
            @Mapping(source = "auditSite.id", target = "auditSiteId"),
            @Mapping(source = "categories.id", target = "categoriesId"),
            @Mapping(source = "categories.label", target = "categoriesLabel"),
            @Mapping(source = "subCategories.id", target = "subCategoriesId"),
            @Mapping(source = "subCategories.blocking", target = "blocking"),
            @Mapping(source = "subCategories.valueType", target = "valueType"),
            @Mapping(source = "firstDecision.id", target = "firstDecisionId"),
            @Mapping(source = "firstDecision.label", target = "firstDecisionLabel"),
            @Mapping(source = "secondDecision.id", target = "secondDecisionId"),
            @Mapping(source = "secondDecision.label", target = "secondDecisionLabel")
    })
    @Override
    AuditSiteLineDto toDto(AuditSiteLine source);

    @Mappings({
            @Mapping(target = "auditSite.id", source = "auditSiteId"),
            @Mapping(target = "categories.id", source = "categoriesId"),
            @Mapping(target = "categories.label", source = "categoriesLabel"),
            @Mapping(target = "subCategories.id", source = "subCategoriesId"),
            @Mapping(target = "subCategories.blocking", source = "blocking"),
            @Mapping(target = "subCategories.valueType", source = "valueType"),
            @Mapping(target = "firstDecision.id", source = "firstDecisionId"),
            @Mapping(target = "firstDecision.label", source = "firstDecisionLabel"),
            @Mapping(target = "secondDecision.id", source = "secondDecisionId"),
            @Mapping(target = "secondDecision.label", source = "secondDecisionLabel")
    })
    @Override
    AuditSiteLine toModel(AuditSiteLineDto target);

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
