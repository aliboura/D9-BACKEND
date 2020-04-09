package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.DecisionTypeDto;
import dz.djezzy.site.acceptance.business.data.entities.DecisionType;
import dz.djezzy.site.acceptance.business.services.DecisionTypeService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.DECISION_TYPE_API)
public class DecisionTypeController extends GenericRestController<DecisionTypeService, DecisionType, DecisionTypeDto, Integer> {
}
