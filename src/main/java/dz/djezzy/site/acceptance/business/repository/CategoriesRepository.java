package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.Categories;
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

    @Query("select c from Categories c where c.status = true order by c.orderNum asc")
    List<Categories> findAllActive();

    @Query("select c from Categories c where c.last = true and c.status = true")
    List<Categories> findLastCategory();

    @Query("select c from Categories c where c.first = false and c.status = true order by c.orderNum asc")
    List<Categories> findAllOnlyFirst();

    @Query("select c from Categories c where c.last = false and c.status = true order by c.orderNum asc")
    List<Categories> findAllOnlyLast();

    @Query("select c from Categories c where c.first = true and c.status = true and c.typeAuditSite.id = ?1")
    List<Categories> findFirstCategoryByTypeAuditSite(Integer typeAuditSiteId);

    List<Categories> findByStatus(Boolean status);

}
