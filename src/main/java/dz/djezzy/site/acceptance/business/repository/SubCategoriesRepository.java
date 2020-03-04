package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.SubCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoriesRepository extends JpaRepository<SubCategories, Long> {
}
