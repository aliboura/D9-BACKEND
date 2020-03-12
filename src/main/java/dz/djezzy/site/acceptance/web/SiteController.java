package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.business.services.SiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.SITE_API)
public class SiteController extends GenericRestController<SiteService, Site, SiteDto, Integer> {

    @Autowired
    private SiteService siteService;

}
