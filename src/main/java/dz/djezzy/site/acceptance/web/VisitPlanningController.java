package dz.djezzy.site.acceptance.web;

import com.sun.mail.util.MailConnectException;
import dz.djezzy.site.acceptance.business.data.dto.*;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import dz.djezzy.site.acceptance.business.services.MailService;
import dz.djezzy.site.acceptance.business.services.UserService;
import dz.djezzy.site.acceptance.business.services.VisitPlanningService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import dz.djezzy.site.acceptance.tools.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
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
    private final MailService mailService;
    private final VisitPlanningService visitPlanningService;

    @Transactional
    @PostMapping("/send")
    public MailResponse<VisitPlanningDto> createVisitAndSendMail(@RequestBody VisitPlanningDto entity) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String[] emails = setMails(entity.getEngineerSiteV1Mail(), entity.getEngineerOMV1Mail());
            MailResponse<String> send = mailService.sendNotifications(
                    false,
                    new MailRequest(entity.getSiteCode(),
                            dateFormat.format(entity.getEngineerSiteDateV1()),
                            entity.getSiteName(),
                            entity.getEngineerSiteV1FullName(),
                            entity.getEngineerOMV1FullName(),
                            null,
                            emails,
                            null));
            if (!send.isSuccess()) {
                return new MailResponse<>("Erreur dans l'envoie du mail.");
            }
            VisitPlanningDto saved = visitPlanningService.save(entity);
            return new MailResponse<>(saved);
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        }
    }

    @PutMapping("/send")
    public MailResponse<VisitPlanningDto> updateVisitAndSendMail(@RequestBody VisitPlanningDto entity) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            List<UserInfo> list = userService.findUserInfo(Arrays.asList(
                    !entity.getAudited() ? entity.getEngineerSiteV1() : entity.getEngineerSiteV2(),
                    !entity.getAudited() ? entity.getEngineerOMV1() : entity.getEngineerOMV2()));
            String[] emails = list.stream().map(x -> x.getEmail()).collect(Collectors.toList()).toArray(new String[0]);
            MailResponse<String> send = mailService.sendNotifications(
                    entity.getAudited(),
                    new MailRequest(entity.getSiteCode(),
                            dateFormat.format(entity.getFirstVisit() ? entity.getEngineerSiteDateV2() : entity.getEngineerSiteDateV1()),
                            entity.getSiteName(),
                            getFullName(entity.getAudited() ? entity.getEngineerSiteV2() : entity.getEngineerSiteV1(), list),
                            getFullName(entity.getAudited() ? entity.getEngineerOMV2() : entity.getEngineerOMV1(), list),
                            null,
                            emails,
                            null));
            if (!send.isSuccess()) {
                return new MailResponse<>("Erreur dans l'envoie du mail.");
            }
            VisitPlanningDto saved = visitPlanningService.save(entity);
            return new MailResponse<>(saved);
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        }
    }

    private String getFullName(String username, List<UserInfo> list) {
        return list.stream().filter(x -> x.getUsername().equalsIgnoreCase(username)).findAny().get().getFullName();
    }

    private String[] setMails(String mailEngineerSite, String mailEngineerOM) {
        List<String> mails = new ArrayList<>();
        if (mailEngineerSite != null && !mails.contains(mailEngineerSite)) {
            mails.add(mailEngineerSite);
        }
        if (mailEngineerOM != null && !mails.contains(mailEngineerSite)) {
            mails.add(mailEngineerOM);
        }
        return mails.toArray(new String[0]);
    }

    private String[] getMails(VisitPlanningDto visitPlanning) {
        if (visitPlanning.getAudited()) {
            return userService.findUsersMail(Arrays.asList(visitPlanning.getEngineerSiteV2(), visitPlanning.getEngineerOMV2()));
        } else {
            return userService.findUsersMail(Arrays.asList(visitPlanning.getEngineerSiteV1(), visitPlanning.getEngineerOMV1()));
        }
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
