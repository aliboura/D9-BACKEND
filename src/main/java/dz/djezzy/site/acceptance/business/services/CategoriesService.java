package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;

import java.util.List;

public interface CategoriesService extends GenericService<Categories, CategoriesDto, Integer> {

    CategoriesDto findFirstCategory();

    CategoriesDto findFirstCategoryByTypeAuditSite(Integer typeAuditSiteId);

    List<CategoriesDto> findByStatus(Boolean status);
}
