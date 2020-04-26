package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.services.DecisionService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.DECISION_API)
public class DecisionController extends GenericRestController<DecisionService, Decision, DecisionDto, Integer> {

    @Autowired
    private DecisionService decisionService;

    @GetMapping(params = {"label", "position"})
    public DecisionDto findByLabelAndPosition(@RequestParam("label") String label, @RequestParam("position") Integer position) {
        return decisionService.findByLabelAndPosition(label, position);
    }
}
