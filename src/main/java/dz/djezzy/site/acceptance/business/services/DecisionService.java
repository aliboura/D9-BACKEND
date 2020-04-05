package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;

import java.util.List;

public interface DecisionService extends GenericService<Decision, DecisionDto, Integer> {

    List<DecisionDto> findByTypeValue(Integer type);

}
