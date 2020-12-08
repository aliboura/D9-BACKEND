package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.repository.CategoriesRepository;
import dz.djezzy.site.acceptance.business.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriesServiceIMPL extends GenericServiceImpl<CategoriesRepository, Categories, CategoriesDto, Integer>
        implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public CategoriesDto findFirstCategory() {
        List<Categories> list = categoriesRepository.findFirstCategory();
        return list != null && !list.isEmpty() ? asDto(list.get(0)) : null;
    }

    @Override
    public CategoriesDto findLastCategory() {
        List<Categories> list = categoriesRepository.findLastCategory();
        return list != null && !list.isEmpty() ? asDto(list.get(0)) : null;
    }

    @Override
    public List<CategoriesDto> findAllActive() {
        return asDto(categoriesRepository.findAllActive());
    }

    @Override
    public List<CategoriesDto> findAllOnlyFirst() {
        return asDto(categoriesRepository.findAllOnlyFirst());
    }

    @Override
    public List<CategoriesDto> findAllOnlyLast() {
        return asDto(categoriesRepository.findAllOnlyLast());
    }

    @Override
    public CategoriesDto findFirstCategoryByTypeAuditSite(Integer typeAuditSiteId) {
        List<Categories> list = categoriesRepository.findFirstCategoryByTypeAuditSite(typeAuditSiteId);
        return list != null && !list.isEmpty() ? asDto(list.get(0)) : null;
    }

    @Override
    public List<CategoriesDto> findByStatus(Boolean status) {
        return asDto(categoriesRepository.findByStatus(status));
    }

    @Override
    public HashMap<String, Integer> getLabelMaps() {
        return (HashMap<String, Integer>) categoriesRepository.findAll().stream().filter(cat -> cat.getStatus()).collect(Collectors.toMap(Categories::getLabel, Categories::getId));
    }
}
