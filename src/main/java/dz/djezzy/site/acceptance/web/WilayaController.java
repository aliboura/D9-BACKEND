package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.WilayaDto;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import dz.djezzy.site.acceptance.business.services.WilayaService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.WILAYA_API)
public class WilayaController extends GenericRestController<WilayaService, Wilaya, WilayaDto, Integer> {
}
