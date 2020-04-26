package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import dz.djezzy.site.acceptance.business.data.entities.Role;
import dz.djezzy.site.acceptance.business.services.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends GenericRestController<RoleService, Role, RoleDto, Long> {
}
