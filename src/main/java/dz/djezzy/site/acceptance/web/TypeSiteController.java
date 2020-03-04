package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.TypeSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.TypeSite;
import dz.djezzy.site.acceptance.business.services.TypeSiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.TYPE_SITE_API)
public class TypeSiteController extends GenericRestController<TypeSiteService, TypeSite, TypeSiteDto, String> {
}
