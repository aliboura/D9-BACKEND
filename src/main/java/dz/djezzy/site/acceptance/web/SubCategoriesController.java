package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.data.dto.SubCategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.SubCategories;
import dz.djezzy.site.acceptance.business.services.SubCategoriesService;
import dz.djezzy.site.acceptance.tools.ApiConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.SUB_CATEGORY_API)
public class SubCategoriesController extends GenericRestController<SubCategoriesService, SubCategories, SubCategoriesDto, Integer> {
}
