package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.DecisionTypeDto;
import dz.djezzy.site.acceptance.business.data.entities.DecisionType;
import dz.djezzy.site.acceptance.business.repository.DecisionTypeRepository;
import dz.djezzy.site.acceptance.business.services.DecisionTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecisionTypeServiceIMPL extends GenericServiceImpl<DecisionTypeRepository, DecisionType, DecisionTypeDto, Integer>
        implements DecisionTypeService {
}
