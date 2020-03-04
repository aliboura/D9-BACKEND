package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.SubCategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.SubCategories;
import dz.djezzy.site.acceptance.business.repository.SubCategoriesRepository;
import dz.djezzy.site.acceptance.business.services.SubCategoriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubCategoriesServiceIMPL extends GenericServiceImpl<SubCategoriesRepository, SubCategories, SubCategoriesDto, Long>
        implements SubCategoriesService {
}
