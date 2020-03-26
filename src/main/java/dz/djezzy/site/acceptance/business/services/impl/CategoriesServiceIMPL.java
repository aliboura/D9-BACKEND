package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.repository.CategoriesRepository;
import dz.djezzy.site.acceptance.business.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
