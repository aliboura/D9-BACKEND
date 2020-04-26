package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import dz.djezzy.site.acceptance.business.data.entities.Role;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapping extends GenericMapper<Role, RoleDto> {

    RoleMapping INSTANCE = Mappers.getMapper(RoleMapping.class);
}
