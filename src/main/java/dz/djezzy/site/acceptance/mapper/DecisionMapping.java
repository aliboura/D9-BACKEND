package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DecisionTypeMapping.class})
public interface DecisionMapping extends GenericMapper<Decision, DecisionDto> {

    DecisionMapping INSTANCE = Mappers.getMapper(DecisionMapping.class);

    @Mappings({
            @Mapping(source = "decisionType.id", target = "decisionTypeId"),
            @Mapping(source = "decisionType.label", target = "decisionTypeLabel"),
    })
    @Override
    DecisionDto toDto(Decision source);

    @Mappings({
            @Mapping(target = "decisionType.id", source = "decisionTypeId"),
            @Mapping(target = "decisionType.label", source = "decisionTypeLabel"),
    })
    @Override
    Decision toModel(DecisionDto target);

}
