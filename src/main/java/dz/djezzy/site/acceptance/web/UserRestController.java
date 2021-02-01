package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.entities.User;
import dz.djezzy.site.acceptance.business.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestController extends GenericRestController<UserService, User, UserDto, Long> {

    private final UserService userService;

    @GetMapping(params = {"username"})
    public UserDto findByUsername(@RequestParam("username") String username) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(username);
        Optional<UserDto> optionalUserDto = userService.findByUsername(new String(decodedBytes));
        return optionalUserDto.isPresent() ? optionalUserDto.get() : null;
    }

    @GetMapping(params = {"matricule"})
    public UserDto findByMatricule(@RequestParam("matricule") String matricule) {
        Optional<UserDto> optionalUserDto = userService.findByMatricule(matricule);
        return optionalUserDto.isPresent() ? optionalUserDto.get() : null;
    }

    @GetMapping(params = {"mail"})
    public UserDto findByEmail(@RequestParam("mail") String mail) {
        Optional<UserDto> optionalUserDto = userService.findByEmail(mail);
        return optionalUserDto.isPresent() ? optionalUserDto.get() : null;
    }

    @GetMapping(params = {"phone"})
    public UserDto findByPhone(@RequestParam("phone") String phone) {
        Optional<UserDto> optionalUserDto = userService.findByPhone(phone);
        return optionalUserDto.isPresent() ? optionalUserDto.get() : null;
    }
}
