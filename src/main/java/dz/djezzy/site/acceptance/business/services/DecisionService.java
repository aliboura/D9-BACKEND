package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;

import java.util.HashMap;
import java.util.List;

public interface DecisionService extends GenericService<Decision, DecisionDto, Integer> {

    DecisionDto findByLabelAndPosition(String label, Integer position);

    HashMap<String, Integer> getDecisionLabel(Integer decisionTypeId);

}
