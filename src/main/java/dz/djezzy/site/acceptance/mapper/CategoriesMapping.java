package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriesMapping extends GenericMapper<Categories, CategoriesDto> {

    CategoriesMapping INSTANCE = Mappers.getMapper(CategoriesMapping.class);

    @Mappings({
            @Mapping(source = "subCategoriesList", target = "listSubCategories")
    })
    @Override
    CategoriesDto toDto(Categories source);

    @Mappings({
            @Mapping(target = "subCategoriesList", source = "listSubCategories")
    })
    @Override
    Categories toModel(CategoriesDto target);
}
