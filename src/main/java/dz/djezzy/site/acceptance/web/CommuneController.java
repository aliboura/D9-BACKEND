package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.CommuneDto;
import dz.djezzy.site.acceptance.business.data.entities.Commune;
import dz.djezzy.site.acceptance.business.services.CommuneService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.COMMUNE_API)
public class CommuneController extends GenericRestController<CommuneService, Commune, CommuneDto, Integer> {
}
