package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;

public interface CategoriesService extends GenericService<Categories, CategoriesDto, Integer> {

    CategoriesDto findFirstCategory();
}
