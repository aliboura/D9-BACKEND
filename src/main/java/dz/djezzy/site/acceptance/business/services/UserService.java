package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.entities.User;

import java.util.Optional;

public interface UserService extends GenericService<User, UserDto, Long> {

    Optional<UserDto> findByUsername(String name);
}
