package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.repository.DecisionRepository;
import dz.djezzy.site.acceptance.business.services.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DecisionServiceIMPL extends GenericServiceImpl<DecisionRepository, Decision, DecisionDto, Integer>
        implements DecisionService {

    @Autowired
    private DecisionRepository decisionRepository;

    @Override
    public List<DecisionDto> findByTypeValue(Integer type) {
        return asDto(decisionRepository.findByTypeValue(type));
    }
}
