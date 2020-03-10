package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.dto.SubCategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.data.entities.SubCategories;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-10T12:22:25+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class CategoriesMappingImpl implements CategoriesMapping {

    @Override
    public List<CategoriesDto> toDto(Collection<Categories> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<CategoriesDto> list = new ArrayList<CategoriesDto>( sourceList.size() );
        for ( Categories categories : sourceList ) {
            list.add( toDto( categories ) );
        }

        return list;
    }

    @Override
    public List<Categories> toModel(Collection<CategoriesDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<Categories> list = new ArrayList<Categories>( targetList.size() );
        for ( CategoriesDto categoriesDto : targetList ) {
            list.add( toModel( categoriesDto ) );
        }

        return list;
    }

    @Override
    public CategoriesDto toDto(Categories source) {
        if ( source == null ) {
            return null;
        }

        CategoriesDto categoriesDto = new CategoriesDto();

        categoriesDto.setListSubCategories( subCategoriesListToSubCategoriesDtoList( source.getSubCategoriesList() ) );
        categoriesDto.setId( source.getId() );
        categoriesDto.setLabel( source.getLabel() );
        categoriesDto.setPosition( source.getPosition() );
        categoriesDto.setStatus( source.getStatus() );

        return categoriesDto;
    }

    @Override
    public Categories toModel(CategoriesDto target) {
        if ( target == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setSubCategoriesList( subCategoriesDtoListToSubCategoriesList( target.getListSubCategories() ) );
        categories.setId( target.getId() );
        categories.setLabel( target.getLabel() );
        categories.setPosition( target.getPosition() );
        categories.setStatus( target.getStatus() );

        return categories;
    }

    protected SubCategoriesDto subCategoriesToSubCategoriesDto(SubCategories subCategories) {
        if ( subCategories == null ) {
            return null;
        }

        SubCategoriesDto subCategoriesDto = new SubCategoriesDto();

        subCategoriesDto.setId( subCategories.getId() );
        subCategoriesDto.setLabel( subCategories.getLabel() );

        return subCategoriesDto;
    }

    protected List<SubCategoriesDto> subCategoriesListToSubCategoriesDtoList(List<SubCategories> list) {
        if ( list == null ) {
            return null;
        }

        List<SubCategoriesDto> list1 = new ArrayList<SubCategoriesDto>( list.size() );
        for ( SubCategories subCategories : list ) {
            list1.add( subCategoriesToSubCategoriesDto( subCategories ) );
        }

        return list1;
    }

    protected SubCategories subCategoriesDtoToSubCategories(SubCategoriesDto subCategoriesDto) {
        if ( subCategoriesDto == null ) {
            return null;
        }

        SubCategories subCategories = new SubCategories();

        subCategories.setId( subCategoriesDto.getId() );
        subCategories.setLabel( subCategoriesDto.getLabel() );

        return subCategories;
    }

    protected List<SubCategories> subCategoriesDtoListToSubCategoriesList(List<SubCategoriesDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SubCategories> list1 = new ArrayList<SubCategories>( list.size() );
        for ( SubCategoriesDto subCategoriesDto : list ) {
            list1.add( subCategoriesDtoToSubCategories( subCategoriesDto ) );
        }

        return list1;
    }
}
