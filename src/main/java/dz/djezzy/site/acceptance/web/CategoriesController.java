package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.services.CategoriesService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstant.CATEGORY_API)
public class CategoriesController extends GenericRestController<CategoriesService, Categories, CategoriesDto, Integer> {

    private final CategoriesService categoriesService;

    @GetMapping("/first")
    public CategoriesDto getFirstCategory() {
        return categoriesService.findFirstCategory();
    }

    @GetMapping(value = "/active")
    public List<CategoriesDto> findAllActive() {
        return categoriesService.findAllActive();
    }

    @GetMapping(value = "/first", params = {"type"})
    public CategoriesDto findFirstCategoryByTypeAuditSite(@RequestParam(value = "type") Integer type) {
        return categoriesService.findFirstCategoryByTypeAuditSite(type);
    }

    @GetMapping(value = "/noFirst")
    public List<CategoriesDto> findAllOnlyFirst() {
        return categoriesService.findAllOnlyFirst();
    }

    @GetMapping(value = "/noLast")
    public List<CategoriesDto> findAllOnlyLast() {
        return categoriesService.findAllOnlyLast();
    }

}
