package dz.djezzy.site.acceptance.web;

import dz.djezzy.site.acceptance.business.services.GenericService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public class GenericRestController<S extends GenericService<T, DTO, ID>, T, DTO, ID> implements Serializable {

    @Autowired
    protected S service;

    @Getter
    @Setter
    protected List<DTO> listAll;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<DTO> findAll() {
        listAll = service.findAll();
        return listAll;
    }


    @GetMapping(params = {"sort", "field"})
    public List<DTO> findAll(@RequestParam(value = "sort") String sort,
                             @RequestParam(value = "field") String field) {
        if (sort.equals("ASC"))
            listAll = service.findAll(Sort.by(Direction.ASC, field));
        else
            listAll = service.findAll(Sort.by(Direction.DESC, field));
        return listAll;
    }

    @GetMapping(params = {"page", "size"})
    public Page<DTO> findAll(@RequestParam(value = "page") int page,
                             @RequestParam(value = "size") int size) {
        return service.findAll(PageRequest.of(page, size));
    }

    @GetMapping(params = {"page", "size", "sort", "field"})
    public Page<DTO> findAll(@RequestParam("page") int page,
                             @RequestParam("size") int size,
                             @RequestParam("sort") String sort,
                             @RequestParam("field") String field) {
        if (sort.equalsIgnoreCase("ASC"))
            return service.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, field)));
        else
            return service.findAll(PageRequest.of(page, size, Sort.by(Direction.DESC, field)));
    }

    @PostMapping
    public @ResponseBody
    DTO create(@RequestBody DTO entity) {
        if (entity != null) {
            return service.save(entity);
        }
        return null;
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<DTO> createAll(@RequestBody List<DTO> list) {
        return (List<DTO>) service.saveAll(list);
    }

    @PutMapping
    public @ResponseBody
    DTO update(@RequestBody DTO entity) {
        return service.save(entity);
    }

    @DeleteMapping
    public void deleteEntity(@RequestBody DTO entity) {
        service.delete(entity);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") ID id) {
        service.deleteById(id);
    }

    @DeleteMapping(value = "/all")
    public void deleteAll(@RequestBody List<DTO> list) {
        service.deleteAll(list);
    }

    @GetMapping(params = {"id"})
    public DTO get(@RequestParam("id") ID id) {
        return service.getOne(id);
    }

    @GetMapping(value = "/count")
    public Long count() {
        return service.count();
    }

}
