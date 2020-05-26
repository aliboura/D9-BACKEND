package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import dz.djezzy.site.acceptance.business.services.VisitPlanningService;
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

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.VISITS_API)
public class VisitPlanningController extends GenericRestController<VisitPlanningService, VisitPlanning, VisitPlanningDto, Long> {

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


}
