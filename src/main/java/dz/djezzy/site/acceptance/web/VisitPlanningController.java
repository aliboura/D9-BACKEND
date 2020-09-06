package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import dz.djezzy.site.acceptance.business.services.UserService;
import dz.djezzy.site.acceptance.business.services.VisitPlanningService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.VISITS_API)
public class VisitPlanningController extends GenericRestController<VisitPlanningService, VisitPlanning, VisitPlanningDto, Long> {

    private final UserService userService;
    private final VisitPlanningService visitPlanningService;

    @GetMapping(params = {"page", "size", "sort", "field", "cities"})
    public Page<VisitPlanningDto> findByCities(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("cities") String citiesIds) {
        String[] ids = citiesIds.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return visitPlanningService.findByCities(cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"site"})
    public VisitPlanningDto findBySiteId(@RequestParam("site") String id) {
        Optional<VisitPlanningDto> opt = visitPlanningService.findBySiteId(Long.parseLong(id));
        return opt.isPresent() ? opt.get() : null;
    }

    @GetMapping(value = "/exist", params = {"site"})
    public Boolean existSite(@RequestParam("site") Long id) {
        return visitPlanningService.findBySiteId(id).isPresent();
    }

    @GetMapping(params = {"page", "size", "sort", "field", "code", "cities"})
    public Page<VisitPlanningDto> findByCode(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("code") String code,
            @RequestParam("cities") String citiesIds) {
        String[] ids = citiesIds.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return visitPlanningService.findByCode(code, cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "engineerSiteV1", "fromDate", "toDate", "cities"})
    Page<VisitPlanningDto> findFirstVisitPlannings(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("engineerSiteV1") String engineerSiteV1,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("cities") String citiesIds) throws ParseException {

        String[] ids = citiesIds.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return visitPlanningService.findFirstVisitPlannings(engineerSiteV1, AppsUtils.getDate(fromDate), AppsUtils.getDate(toDate), cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(params = {"page", "size", "sort", "field", "engineerSiteV2", "fromDate", "toDate", "cities"})
    Page<VisitPlanningDto> findSecondVisitPlannings(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("engineerSiteV2") String engineerSiteV2,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("cities") String citiesIds) throws ParseException {

        String[] ids = citiesIds.split(",");
        List<Integer> cities = Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        return visitPlanningService.findSecondVisitPlannings(engineerSiteV2, AppsUtils.getDate(fromDate), AppsUtils.getDate(toDate), cities, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field)));
    }

    @GetMapping(value = "/count", params = {"username"})
    public Integer notificationCount(
            @RequestParam("username") String username) {
        Optional<UserDto> user = userService.findByUsername(username);
        if (user.isPresent()) {
            Set<RoleDto> roles = user.get().getRoleSet();
            if (user.get().isSiteEngineer(roles))
                return visitPlanningService.countEngineer(username, new Date());
            else if (user.get().isOMEngineer(roles))
                return visitPlanningService.countEngineerOM(username, new Date());
            else
                return 0;
        }
        return 0;
    }

    @GetMapping(params = {"page", "size", "sort", "field", "username"})
    public Page<VisitPlanningDto> myPlannings(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam("field") String field,
            @RequestParam("username") String username) {
        Optional<UserDto> user = userService.findByUsername(username);
        Pageable pageable = PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), field));
        if (user.isPresent()) {
            Set<RoleDto> roles = user.get().getRoleSet();
            if (user.get().isSiteEngineer(roles))
                return visitPlanningService.findEngineerPlannings(username, pageable);
            else if (user.get().isOMEngineer(roles))
                return visitPlanningService.findOMPlannings(username, pageable);
            else
                return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }


}
