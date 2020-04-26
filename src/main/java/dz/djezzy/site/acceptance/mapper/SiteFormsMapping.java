package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.SiteFormsDto;
import dz.djezzy.site.acceptance.business.data.entities.SiteForms;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DecisionMapping.class})
public interface SiteFormsMapping extends GenericMapper<SiteForms, SiteFormsDto> {

    SiteFormsMapping INSTANCE = Mappers.getMapper(SiteFormsMapping.class);

    @Mappings({
            @Mapping(source = "decision.id", target = "decisionId"),
            @Mapping(source = "decision.label", target = "decisionLabel")
    })
    @Override
    SiteFormsDto toDto(SiteForms source);

    @Mappings({
            @Mapping(target = "decision.id", source = "decisionId"),
            @Mapping(target = "decision.label", source = "decisionLabel")
    })
    @Override
    SiteForms toModel(SiteFormsDto target);

    @AfterMapping
    default SiteForms doAfterMapping(@MappingTarget SiteForms entity) {
        if (entity != null && entity.getDecision().getId() == null) {
            entity.setDecision(null);
        }
        return entity;
    }
}
