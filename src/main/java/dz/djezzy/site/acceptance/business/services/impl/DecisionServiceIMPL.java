package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.repository.DecisionRepository;
import dz.djezzy.site.acceptance.business.services.DecisionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecisionServiceIMPL extends GenericServiceImpl<DecisionRepository, Decision, DecisionDto, Integer>
        implements DecisionService {
}
