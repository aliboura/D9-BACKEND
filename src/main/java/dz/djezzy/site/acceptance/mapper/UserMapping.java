package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.entities.User;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RoleMapping.class, WilayaRegionMapping.class})
public interface UserMapping extends GenericMapper<User, UserDto> {

    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    @Mappings({
            @Mapping(source = "roles", target = "roleSet"),
            @Mapping(source = "wilayas", target = "wilayaSet"),
    })
    @Override
    UserDto toDto(User source);

    @Mappings({
            @Mapping(target = "roles", source = "roleSet"),
            @Mapping(target = "wilayas", source = "wilayaSet"),
    })
    @Override
    User toModel(UserDto target);
}
