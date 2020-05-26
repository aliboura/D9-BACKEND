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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.SITE_API)
public class SiteController extends GenericRestController<SiteService, Site, SiteDto, Long> {

    private final SiteService siteService;

    @GetMapping(params = {"page", "size", "sort", "field", "codeSite", "userV1"})
    public Page<SiteDto> findSites(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("codeSite") String codeSite,
            @RequestParam("userV1") String userV1) {

        return service.findSites(codeSite, userV1, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
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

        return service.findBySites(false, cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
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

        return service.findByLikeCodeSite(false, "%" + codeSite.toLowerCase() + "%", cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "codeSite", "wilayas", "userV1"})
    public Page<SiteDto> findSites(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("codeSite") String codeSite,
            @RequestParam("wilayas") String wilayas,
            @RequestParam("userV1") String userV1) {

        String[] ids = wilayas.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return service.findSites(codeSite, cities, userV1, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "city", "user"})
    public Page<SiteDto> findSitesByUserWilayas(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("city") String wilayas,
            @RequestParam("user") String userV1) {

        String[] ids = wilayas.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return service.findSitesByUserWilayas(cities, userV1, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

}
