package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.dto.UserInfo;
import dz.djezzy.site.acceptance.business.data.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericService<User, UserDto, Long> {

    Optional<UserDto> findByUsername(String name);
    Optional<UserDto> findByMatricule(String matricule);
    Optional<UserDto> findByPhone(String matricule);
    Optional<UserDto> findByEmail(String email);
    String[] findUsersMail(List<String> users);
    List<UserInfo> findUserInfo(List<String> users);
}
