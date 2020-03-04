package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.repository.CategoriesRepository;
import dz.djezzy.site.acceptance.business.services.CategoriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriesServiceIMPL extends GenericServiceImpl<CategoriesRepository, Categories, CategoriesDto, Long>
        implements CategoriesService {
}
