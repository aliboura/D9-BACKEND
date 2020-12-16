package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.MailRequest;
import dz.djezzy.site.acceptance.business.data.dto.RoleDto;
import dz.djezzy.site.acceptance.business.data.dto.UserDto;
import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import dz.djezzy.site.acceptance.business.services.UserService;
import dz.djezzy.site.acceptance.business.services.VisitPlanningService;
import dz.djezzy.site.acceptance.exception.ApplicationException;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.VISITS_API)
public class VisitPlanningController extends GenericRestController<VisitPlanningService, VisitPlanning, VisitPlanningDto, Long> {

    private final UserService userService;
    private final VisitPlanningService visitPlanningService;

    @PostMapping
    @Override
    public VisitPlanningDto create(@RequestBody VisitPlanningDto entity) {
        VisitPlanningDto saved = visitPlanningService.save(entity);
        if (saved != null) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String[] emails = setMails(entity.getEngineerSiteV1Mail(), entity.getEngineerOMV1Mail());
                String send = visitPlanningService.sendV1Notifications(new MailRequest(entity.getSiteCode(), dateFormat.format(entity.getEngineerSiteDateV1()), entity.getEngineerSiteV1FullName(), entity.getEngineerOMV1FullName(), emails));
                if (send != "OK") {
                    throw new ApplicationException("Mail non envoyé");
                }
            } catch (UnsupportedEncodingException e) {
                throw new ApplicationException(e.getMessage());
            } catch (MessagingException e) {
                throw new ApplicationException(e.getMessage());
            }
        }
        return saved;
    }

    @PutMapping
    @Override
    public VisitPlanningDto update(VisitPlanningDto entity) {
        VisitPlanningDto saved = visitPlanningService.save(entity);
        if (saved != null && saved.getAudited()) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String[] emails = setMails(entity.getEngineerSiteV1Mail(), entity.getEngineerOMV1Mail());
                String send = visitPlanningService.sendV1Notifications(new MailRequest(entity.getSiteCode(), dateFormat.format(entity.getEngineerSiteDateV2()), entity.getEngineerSiteV2FullName(), entity.getEngineerOMV2FullName(), emails));
                if (send != "OK") {
                    throw new ApplicationException("Mail non envoyé");
                }
            } catch (UnsupportedEncodingException e) {
                throw new ApplicationException(e.getMessage());
            } catch (MessagingException e) {
                throw new ApplicationException(e.getMessage());
            }
        }
        return saved;
    }

    private String[] setMails(String mailEngineerSite, String mailEngineerOM) {
        List<String> mails = new ArrayList<>();
        if (mailEngineerSite != null) {
            mails.add(mailEngineerSite);
        }
        if (mailEngineerOM != null) {
            mails.add(mailEngineerOM);
        }
        return mails.toArray(new String[0]);
    }

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
