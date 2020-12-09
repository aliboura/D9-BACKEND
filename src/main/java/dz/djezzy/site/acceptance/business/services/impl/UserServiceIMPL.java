package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.entities.User;
import dz.djezzy.site.acceptance.business.repository.UserRepository;
import dz.djezzy.site.acceptance.business.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL extends GenericServiceImpl<UserRepository, User, UserDto, Long>
        implements UserService {

    private final UserRepository UserRepository;

    public UserServiceIMPL(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @Override
    public Optional<UserDto> findByUsername(String name) {
        Optional<User> opt = UserRepository.findByUsername(name);
        return opt.isPresent() ? Optional.of(asDto(opt.get())) : Optional.empty();
    }

    @Override
    public Optional<UserDto> findByMatricule(String matricule) {
        Optional<User> opt = UserRepository.findByMatricule(matricule);
        return opt.isPresent() ? Optional.of(asDto(opt.get())) : Optional.empty();
    }

    @Override
    public Optional<UserDto> findByPhone(String matricule) {
        Optional<User> opt = UserRepository.findByPhone(matricule);
        return opt.isPresent() ? Optional.of(asDto(opt.get())) : Optional.empty();
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> opt = UserRepository.findByEmail(email);
        return opt.isPresent() ? Optional.of(asDto(opt.get())) : Optional.empty();
    }
}
