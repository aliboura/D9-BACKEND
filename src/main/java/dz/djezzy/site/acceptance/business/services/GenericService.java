package dz.djezzy.site.acceptance.business.services;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, DTO, ID> {

    List<DTO> findAll();

    List<DTO> findAll(Sort sort);

    Page<DTO> findAll(Pageable pageable);

    Page<DTO> findAll(Predicate predicate, Pageable pageable);

    Page<DTO> findAll(@Nullable Specification<T> spec, Pageable pageable);

    List<DTO> findAll(@Nullable Specification<T> spec, Sort sort);

    DTO save(DTO dto);

    Iterable<DTO> saveAll(Iterable<DTO> iterable);

    void deleteById(ID id);

    void delete(DTO dto);

    void deleteAll(Iterable<DTO> iterable);

    DTO getOne(ID id);

    Optional<DTO> findById(ID id);

    Long count();

    List<DTO> findAllByExample(DTO example);
}
