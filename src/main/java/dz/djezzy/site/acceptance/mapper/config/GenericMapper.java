package dz.djezzy.site.acceptance.mapper.config;

import java.util.Collection;
import java.util.List;

public interface GenericMapper<T, D> {

    /**
     * Converts given model to dto.
     */
    D toDto(T source);

    /**
     * Converts given dto to model.
     */
    T toModel(D target);


    /**
     * Converts given model list to dto list.
     */
    List<D> toDto(Collection<T> sourceList);

    /**
     * Converts given dto list to model list.
     */
    List<T> toModel(Collection<D> targetList);

}
