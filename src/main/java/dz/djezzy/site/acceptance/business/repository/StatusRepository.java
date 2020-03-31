package dz.djezzy.site.acceptance.business.repository;

import dz.djezzy.site.acceptance.business.data.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>, QuerydslPredicateExecutor<Status>, JpaSpecificationExecutor<Status> {

    @Query("select s from Status s where s.first = true")
    List<Status> findFirstStatus();

    public Optional<Status> findByLabel(String label);


}
