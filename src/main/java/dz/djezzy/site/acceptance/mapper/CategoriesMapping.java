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
            @Mapping(source = "typeAuditSite.id", target = "typeAuditSiteId"),
            @Mapping(source = "typeAuditSite.label", target = "typeAuditSiteLabel"),
            @Mapping(source = "next.id", target = "nextCatId"),
            @Mapping(source = "next.label", target = "nextCatLabel"),
            @Mapping(source = "next.orderNum", target = "nextCatOrder"),
            @Mapping(source = "previous.id", target = "previousCatId"),
            @Mapping(source = "previous.label", target = "previousCatLabel"),
            @Mapping(source = "previous.orderNum", target = "previousCatOrder"),
            @Mapping(source = "subCategoriesList", target = "listSubCategories")
    })
    @Override
    CategoriesDto toDto(Categories source);

    @Mappings({
            @Mapping(target = "typeAuditSite.id", source = "typeAuditSiteId"),
            @Mapping(target = "typeAuditSite.label", source = "typeAuditSiteLabel"),
            @Mapping(target = "next.id", source = "nextCatId"),
            @Mapping(target = "next.label", source = "nextCatLabel"),
            @Mapping(target = "next.orderNum", source = "nextCatOrder"),
            @Mapping(target = "previous.id", source = "previousCatId"),
            @Mapping(target = "previous.label", source = "previousCatLabel"),
            @Mapping(target = "previous.orderNum", source = "previousCatOrder"),
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
