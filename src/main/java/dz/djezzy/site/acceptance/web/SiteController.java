package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.business.services.SiteService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.SITE_API)
public class SiteController extends GenericRestController<SiteService, Site, SiteDto, Long> {

    private final SiteService siteService;

    @GetMapping(params = {"code"})
    public SiteDto findByCodeSite(@RequestParam("code") String codeSite) {
        return siteService.findByCodeSite(codeSite);
    }

    @GetMapping(params = {"page", "size", "sort", "field", "codeSite", "username"})
    public Page<SiteDto> findSites(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("codeSite") String codeSite,
            @RequestParam("username") String username) {

        return siteService.findSites(codeSite, username, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "cities"})
    public Page<SiteDto> findBySites(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("cities") String citiesIds) {

        String[] ids = citiesIds.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return siteService.findBySites(false, cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "codeSite", "cities"})
    public Page<SiteDto> findByLikeCodeSite(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("codeSite") String codeSite,
            @RequestParam("cities") String citiesIds) {

        String[] ids = citiesIds.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return siteService.findByLikeCodeSite(false, "%" + codeSite.toLowerCase() + "%", cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "codeSite", "wilayas", "username"})
    public Page<SiteDto> findSites(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("codeSite") String codeSite,
            @RequestParam("wilayas") String wilayas,
            @RequestParam("username") String username) {

        String[] ids = wilayas.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return siteService.findSites(codeSite, cities, username, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "user"})
    public Page<SiteDto> findSitesByUserWilayas(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("user") String username) {

        return siteService.findSitesByUserWilayas(username, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "wilayas", "username"})
    public Page<SiteDto> findByCitiesUserV1(@RequestParam("page") int page,
                                            @RequestParam("size") int size,
                                            @RequestParam("sort") String sort,
                                            @RequestParam("field") String field,
                                            @RequestParam("wilayas") String wilayas,
                                            @RequestParam("username") String username) {
        String[] ids = wilayas.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return siteService.findByCitiesUserV1(false, cities, username, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

}
