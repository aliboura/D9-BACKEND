package dz.djezzy.site.acceptance.business.services.impl;

import com.querydsl.core.types.Predicate;
import dz.djezzy.site.acceptance.business.services.GenericService;
import dz.djezzy.site.acceptance.mapper.GenericMapperService;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class GenericServiceImpl<S extends JpaRepository<T, ID> & QuerydslPredicateExecutor<T> & JpaSpecificationExecutor<T>, T, DTO, ID>
        implements GenericService<T, DTO, ID> {

    @Autowired
    protected S dao;

    @Autowired
    private GenericMapperService mapperService;


    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return toDto(dao.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll(Sort sort) {
        return toDto(dao.findAll(sort));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DTO> findAll(Pageable pageable) {
        return dao.findAll(pageable).map(data -> toDto(data));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DTO> findAll(Predicate predicate, Pageable pageable) {
        return dao.findAll(predicate, pageable).map(data -> toDto(data));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DTO> findAll(Specification<T> spec, Pageable pageable) {
        return dao.findAll(spec, pageable).map(data -> toDto(data));
    }

    @Override
    public List<DTO> findAll(Specification<T> spec, Sort sort) {
        return toDto(dao.findAll(spec, sort));
    }


    @Override
    @Transactional
    public DTO save(DTO dto) {
        T object = toObject(dto);
        T saved = dao.save(object);
        return toDto(saved);
    }

    @Override
    @Transactional
    public Iterable<DTO> saveAll(Iterable<DTO> iterable) {
        if (iterable != null) {
            iterable.forEach(x -> save(x));
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(DTO dto) {
        dao.delete(toObject(dto));
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<DTO> iterable) {
        if (iterable != null) {
            iterable.forEach(x -> delete(x));
        }
    }

    @Override
    public DTO getOne(ID id) {
        return toDto(dao.getOne(id));
    }

    @Override
    public Optional<DTO> findById(ID id) {
        Optional<T> opt = dao.findById(id);
        return opt.isPresent() ? Optional.of(toDto(opt.get())) : Optional.empty();
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public List<DTO> findAllByExample(DTO example) {
        return null;
    }

    protected DTO toDto(T object) {
        return getMapper().toDto(object);
    }

    protected List<DTO> toDto(List<T> objects) {
        return getMapper().toDto(objects);
    }

    protected T toObject(DTO dto) {
        return getMapper().toModel(dto);
    }

    protected List<T> toObject(List<DTO> dtos) {
        return getMapper().toModel(dtos);
    }

    private GenericMapper<T, DTO> getMapper() {
        return mapperService.getMapper((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1],
                (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2]);
    }

}
