package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.SubCategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.SubCategories;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DecisionMapping.class})
public interface SubCategoriesMapping extends GenericMapper<SubCategories, SubCategoriesDto> {

    SubCategoriesMapping INSTANCE = Mappers.getMapper(SubCategoriesMapping.class);

    @Mappings({
            @Mapping(source = "categories.id", target = "categoriesId"),
            @Mapping(source = "categories.label", target = "categoriesLabel"),
            @Mapping(source = "decisions", target = "decisionsList")
    })
    @Override
    SubCategoriesDto toDto(SubCategories source);

    @Mappings({
            @Mapping(target = "categories.id", source = "categoriesId"),
            @Mapping(target = "categories.label", source = "categoriesLabel"),
            @Mapping(target = "decisions", source = "decisionsList")
    })
    @Override
    SubCategories toModel(SubCategoriesDto target);


}
