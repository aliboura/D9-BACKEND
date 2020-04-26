package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.Status;
import dz.djezzy.site.acceptance.business.services.StatusService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(ApiConstant.STATUS_API)
public class StatusController extends GenericRestController<StatusService, Status, StatusDto, Integer> {

    @Autowired
    private StatusService statusService;

    @GetMapping("/first")
    public StatusDto getFirstStatus() {
        return statusService.findFirstStatus();
    }

    @GetMapping(params = {"label"})
    public StatusDto findByLabel(@RequestParam("label") String label) {
        Optional<StatusDto> opt = statusService.findByLabel(label);
        return opt.isPresent() ? opt.get() : null;
    }
}
