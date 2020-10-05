package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.repository.DecisionRepository;
import dz.djezzy.site.acceptance.business.services.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DecisionServiceIMPL extends GenericServiceImpl<DecisionRepository, Decision, DecisionDto, Integer>
        implements DecisionService {

    @Autowired
    private DecisionRepository decisionRepository;

    @Override
    public DecisionDto findByLabelAndPosition(String label, Integer position) {
        return asDto(decisionRepository.findByLabelAndPosition(label, position));
    }

    @Override
    public HashMap<String, Integer> getDecisionLabel(Integer decisionTypeId) {
        return (HashMap<String, Integer>) decisionRepository.findAll().stream()
                .filter(dec -> dec.getStatus() && dec.getDecisionType().getId() == decisionTypeId)
                .collect(Collectors.toMap(Decision::getLabel, Decision::getId));
    }
}
