package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {SubCategoriesMapping.class})
public interface CategoriesMapping extends GenericMapper<Categories, CategoriesDto> {

    CategoriesMapping INSTANCE = Mappers.getMapper(CategoriesMapping.class);

    @Mappings({
            @Mapping(source = "next.id", target = "nextCatId"),
            @Mapping(source = "next.label", target = "nextCatLabel"),
            @Mapping(source = "previous.id", target = "previousCatId"),
            @Mapping(source = "previous.label", target = "previousCatLabel"),
            @Mapping(source = "subCategoriesList", target = "listSubCategories")
    })
    @Override
    CategoriesDto toDto(Categories source);

    @Mappings({
            @Mapping(target = "next.id", source = "nextCatId"),
            @Mapping(target = "next.label", source = "nextCatLabel"),
            @Mapping(target = "previous.id", source = "previousCatId"),
            @Mapping(target = "previous.label", source = "previousCatLabel"),
            @Mapping(target = "subCategoriesList", source = "listSubCategories")
    })
    @Override
    Categories toModel(CategoriesDto target);

    @AfterMapping
    default Categories doAfterMapping(@MappingTarget Categories categories) {
        if (categories != null
                && categories.getPrevious() != null
                && categories.getPrevious().getId() == null) {
            categories.setPrevious(null);
        }
        if (categories != null
                && categories.getNext() != null
                && categories.getNext().getId() == null) {
            categories.setNext(null);
        }
        return categories;
    }
}
