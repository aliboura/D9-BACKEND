package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.Status;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapping extends GenericMapper<Status, StatusDto> {

    StatusMapping INSTANCE = Mappers.getMapper(StatusMapping.class);

    @Override
    StatusDto toDto(Status source);

    @Override
    Status toModel(StatusDto target);

    @Override
    List<StatusDto> toDto(Collection<Status> sourceList);

    @Override
    List<Status> toModel(Collection<StatusDto> targetList);
}
