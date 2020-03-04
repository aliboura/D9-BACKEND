package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DecisionMapping extends GenericMapper<Decision, DecisionDto> {

    DecisionMapping INSTANCE = Mappers.getMapper(DecisionMapping.class);

    @Override
    DecisionDto toDto(Decision source);

    @Override
    Decision toModel(DecisionDto target);

}
