package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;

import java.util.HashMap;
import java.util.List;

public interface CategoriesService extends GenericService<Categories, CategoriesDto, Integer> {

    CategoriesDto findFirstCategory();

    CategoriesDto findLastCategory();

    List<CategoriesDto> findAllActive();

    List<CategoriesDto> findAllOnlyFirst();

    List<CategoriesDto> findAllOnlyLast();

    CategoriesDto findFirstCategoryByTypeAuditSite(Integer typeAuditSiteId);

    List<CategoriesDto> findByStatus(Boolean status);

    HashMap<String, Integer> getLabelMaps();
}
