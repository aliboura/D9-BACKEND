package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.DecisionTypeDto;
import dz.djezzy.site.acceptance.business.data.entities.DecisionType;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DecisionMapping.class})
public interface DecisionTypeMapping extends GenericMapper<DecisionType, DecisionTypeDto> {

    DecisionTypeMapping INSTANCE = Mappers.getMapper(DecisionTypeMapping.class);

    @Mappings({
            @Mapping(source = "decisions", target = "decisionList")
    })
    @Override
    DecisionTypeDto toDto(DecisionType source);

    @Mappings({
            @Mapping(target = "decisions", source = "decisionList")
    })
    @Override
    DecisionType toModel(DecisionTypeDto target);
}
