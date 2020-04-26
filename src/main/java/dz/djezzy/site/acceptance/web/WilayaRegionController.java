package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.WilayaRegionDto;
import dz.djezzy.site.acceptance.business.data.entities.WilayaRegion;
import dz.djezzy.site.acceptance.business.services.WilayaRegionService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.WILAYA_REGION_API)
public class WilayaRegionController extends GenericRestController<WilayaRegionService, WilayaRegion, WilayaRegionDto, Integer> {

    private final WilayaRegionService wilayaRegionService;

    @GetMapping(params = {"regionId"})
    public List<WilayaRegionDto> findByRegionId(@RequestParam("regionId") String regionId) {
        return wilayaRegionService.findByRegionId(regionId);
    }

}
