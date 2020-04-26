package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.entities.User;
import dz.djezzy.site.acceptance.business.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController extends GenericRestController<UserService, User, UserDto, Long> {

    private final UserService userService;


    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = {"username"})
    public UserDto findByUsername(@RequestParam("username") String name) {
        Optional<UserDto> optionalUserDto = userService.findByUsername(name);
        return optionalUserDto.isPresent() ? optionalUserDto.get() : null;
    }
}
