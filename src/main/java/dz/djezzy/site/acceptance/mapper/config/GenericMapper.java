package dz.djezzy.site.acceptance.mapper.config;

import java.util.Collection;
import java.util.List;

public interface GenericMapper<T, DTO> {

    /**
     * Converts given model to dto.
     */
    DTO toDto(T source);

    /**
     * Converts given dto to model.
     */
    T toModel(DTO target);


    /**
     * Converts given model list to dto list.
     */
    List<DTO> toDto(Collection<T> sourceList);

    /**
     * Converts given dto list to model list.
     */
    List<T> toModel(Collection<DTO> targetList);

}
