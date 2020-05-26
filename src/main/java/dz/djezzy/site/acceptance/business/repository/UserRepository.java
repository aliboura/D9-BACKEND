package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String name);

    @Query(value = "select distinct(u.*) from depdata.user u, depdata.role r, depdata.user_role ur, depdata.user_wilaya w " +
            "where u.id = ur.user_id " +
            "and r.id = ur.role_id " +
            "and w.user_id = u.id " +
            "and u.username = ?1 " +
            "and r.label = ?2 " +
            "and w.wilaya_id IN ?3",
            countQuery = "select count(distinct(u.*)) from depdata.user u, depdata.role r, depdata.user_role ur, depdata.user_wilaya w " +
                    "where u.id = ur.user_id " +
                    "and r.id = ur.role_id " +
                    "and w.user_id = u.id " +
                    "and u.username = ?1 " +
                    "and r.label = ?2 " +
                    "and w.wilaya_id IN ?3",
            nativeQuery = true)
    Page<User> findUserByRoleFromCities(String username, String role, List<Integer> cities, Pageable pageable);
}
