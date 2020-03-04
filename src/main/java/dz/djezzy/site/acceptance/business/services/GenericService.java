package dz.djezzy.site.acceptance.business.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, DTO, ID> {

    public List<DTO> findAll();

    public List<DTO> findAll(Sort sort);

    public Page<DTO> findAll(Pageable pageable);

    public DTO save(DTO dto);

    public Iterable<DTO> saveAll(Iterable<DTO> iterable);

    public void deleteById(ID id);

    public void delete(DTO dto);

    public void deleteAll(Iterable<DTO> iterable);

    public DTO getOne(ID id);

    public Optional<DTO> findById(ID id);

    public Long count();

    public List<DTO> findAllByExample(DTO example);
}
