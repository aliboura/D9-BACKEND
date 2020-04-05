package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.services.DecisionService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.DECISION_API)
public class DecisionController extends GenericRestController<DecisionService, Decision, DecisionDto, Integer> {

    @Autowired
    private DecisionService decisionService;

    @GetMapping(params = {"type"})
    public List<DecisionDto> findByTypeValue(@RequestParam("type") Integer type) {
        return decisionService.findByTypeValue(type);
    }
}
