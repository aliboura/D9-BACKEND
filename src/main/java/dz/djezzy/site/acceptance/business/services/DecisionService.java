package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;

public interface DecisionService extends GenericService<Decision, DecisionDto, Integer> {

    DecisionDto findByLabelAndPosition(String label, Integer position);

}
