package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.services.CategoriesService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(ApiConstant.CATEGORY_API)
public class CategoriesController extends GenericRestController<CategoriesService, Categories, CategoriesDto, Integer> {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/first")
    public CategoriesDto getFirstCategory() {
        return categoriesService.findFirstCategory();
    }
}
