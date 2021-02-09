package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.dto.UserInfo;
import dz.djezzy.site.acceptance.business.data.entities.User;
import dz.djezzy.site.acceptance.business.repository.UserRepository;
import dz.djezzy.site.acceptance.business.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceIMPL extends GenericServiceImpl<UserRepository, User, UserDto, Long>
        implements UserService {

    private final UserRepository userRepository;

    public UserServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDto> findByUsername(String name) {
        Optional<User> opt = userRepository.findByUsername(name);
        return opt.isPresent() ? Optional.of(toDto(opt.get())) : Optional.empty();
    }

    @Override
    public Optional<UserDto> findByMatricule(String matricule) {
        Optional<User> opt = userRepository.findByMatricule(matricule);
        return opt.isPresent() ? Optional.of(toDto(opt.get())) : Optional.empty();
    }

    @Override
    public Optional<UserDto> findByPhone(String matricule) {
        Optional<User> opt = userRepository.findByPhone(matricule);
        return opt.isPresent() ? Optional.of(toDto(opt.get())) : Optional.empty();
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> opt = userRepository.findByEmail(email);
        return opt.isPresent() ? Optional.of(toDto(opt.get())) : Optional.empty();
    }

    @Override
    public String[] findUsersMail(List<String> users) {
        return userRepository.findUsersMail(users);
    }

    @Override
    public List<UserInfo> findUserInfo(List<String> users) {
        List<User> infos = userRepository.findUserInfo(users);
        return infos.stream().map(x -> new UserInfo(x.getUsername(), x.getEmail(), x.getFullName())).collect(Collectors.toList());
    }
}
