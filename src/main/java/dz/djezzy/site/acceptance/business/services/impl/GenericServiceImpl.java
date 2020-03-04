package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.services.GenericService;
import dz.djezzy.site.acceptance.mapper.GenericMapperService;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class GenericServiceImpl<S extends JpaRepository<T, ID>, T, DTO, ID>
        implements GenericService<T, DTO, ID> {

    @Autowired
    protected S dao;

    @Autowired
    private GenericMapperService mapperService;


    @Override
    public List<DTO> findAll() {
        return asDto(dao.findAll());
    }

    @Override
    public List<DTO> findAll(Sort sort) {
        return asDto(dao.findAll(sort));
    }

    @Override
    public Page<DTO> findAll(Pageable pageable) {
        return dao.findAll(pageable).map(data -> asDto(data));
    }

    @Override
    public DTO save(DTO dto) {
        T object = asObject(dto);
        T saved = dao.save(object);
        return asDto(saved);
    }

    @Override
    public Iterable<DTO> saveAll(Iterable<DTO> iterable) {
        if (iterable != null) {
            iterable.forEach(x -> save(x));
        }
        return null;
    }

    @Override
    public void deleteById(ID id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(DTO dto) {
        dao.delete(asObject(dto));
    }

    @Override
    public void deleteAll(Iterable<DTO> iterable) {
        if (iterable != null) {
            iterable.forEach(x -> delete(x));
        }
    }

    @Override
    public DTO getOne(ID id) {
        return asDto(dao.getOne(id));
    }

    @Override
    public Optional<DTO> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public List<DTO> findAllByExample(DTO example) {
        return null;
    }

    private DTO asDto(T object) {
        return getMapper().toDto(object);
    }

    private List<DTO> asDto(List<T> objects) {
        return getMapper().toDto(objects);
    }

    private T asObject(DTO dto) {
        return getMapper().toModel(dto);
    }

    private List<T> asObject(List<DTO> dtos) {
        return getMapper().toModel(dtos);
    }

    private GenericMapper<T, DTO> getMapper() {
        return mapperService.getMapper((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1],
                (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2]);
    }

}
