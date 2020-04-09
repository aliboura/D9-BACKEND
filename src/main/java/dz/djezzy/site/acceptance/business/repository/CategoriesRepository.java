package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer>, QuerydslPredicateExecutor<Categories>, JpaSpecificationExecutor<Categories> {

    @Query("select c from Categories c where c.first = true and c.status = true")
    List<Categories> findFirstCategory();

    @Query("select c from Categories c where c.first = true and c.status = true and c.typeAuditSite.id = ?1")
    List<Categories> findFirstCategoryByTypeAuditSite(Integer typeAuditSiteId);
}
