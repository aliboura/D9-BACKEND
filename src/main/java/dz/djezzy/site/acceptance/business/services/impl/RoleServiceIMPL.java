package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import dz.djezzy.site.acceptance.business.data.entities.Role;
import dz.djezzy.site.acceptance.business.repository.RoleRepository;
import dz.djezzy.site.acceptance.business.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceIMPL extends GenericServiceImpl<RoleRepository, Role, RoleDto, Long>
        implements RoleService {
}
