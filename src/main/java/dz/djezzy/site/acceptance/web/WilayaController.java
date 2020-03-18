package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.WilayaDto;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import dz.djezzy.site.acceptance.business.services.WilayaService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.WILAYA_API)
public class WilayaController extends GenericRestController<WilayaService, Wilaya, WilayaDto, Integer> {

    @CrossOrigin("*")
    @GetMapping(value = "/by_region")
    public List<WilayaDto> findByRegionId(@RequestParam(value = "regionId") String regionId) {
        return service.findByRegionId(regionId);
    }

}
