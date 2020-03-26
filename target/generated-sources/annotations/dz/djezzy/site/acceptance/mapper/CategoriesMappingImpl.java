package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.CategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-26T15:49:37+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class CategoriesMappingImpl implements CategoriesMapping {

    @Autowired
    private SubCategoriesMapping subCategoriesMapping;

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

        categoriesDto.setPreviousCatId( sourcePreviousId( source ) );
        categoriesDto.setListSubCategories( subCategoriesMapping.toDto( source.getSubCategoriesList() ) );
        categoriesDto.setNextCatLabel( sourceNextLabel( source ) );
        categoriesDto.setNextCatId( sourceNextId( source ) );
        categoriesDto.setPreviousCatLabel( sourcePreviousLabel( source ) );
        categoriesDto.setId( source.getId() );
        categoriesDto.setLabel( source.getLabel() );
        categoriesDto.setPosition( source.getPosition() );
        categoriesDto.setStatus( source.getStatus() );
        categoriesDto.setFirst( source.getFirst() );
        categoriesDto.setLast( source.getLast() );

        return categoriesDto;
    }

    @Override
    public Categories toModel(CategoriesDto target) {
        if ( target == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setNext( categoriesDtoToCategories( target ) );
        categories.setPrevious( categoriesDtoToCategories1( target ) );
        categories.setSubCategoriesList( subCategoriesMapping.toModel( target.getListSubCategories() ) );
        categories.setId( target.getId() );
        categories.setLabel( target.getLabel() );
        categories.setPosition( target.getPosition() );
        categories.setStatus( target.getStatus() );
        categories.setFirst( target.getFirst() );
        categories.setLast( target.getLast() );

        Categories target1 = doAfterMapping( categories );
        if ( target1 != null ) {
            return target1;
        }

        return categories;
    }

    private Integer sourcePreviousId(Categories categories) {
        if ( categories == null ) {
            return null;
        }
        Categories previous = categories.getPrevious();
        if ( previous == null ) {
            return null;
        }
        Integer id = previous.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String sourceNextLabel(Categories categories) {
        if ( categories == null ) {
            return null;
        }
        Categories next = categories.getNext();
        if ( next == null ) {
            return null;
        }
        String label = next.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private Integer sourceNextId(Categories categories) {
        if ( categories == null ) {
            return null;
        }
        Categories next = categories.getNext();
        if ( next == null ) {
            return null;
        }
        Integer id = next.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String sourcePreviousLabel(Categories categories) {
        if ( categories == null ) {
            return null;
        }
        Categories previous = categories.getPrevious();
        if ( previous == null ) {
            return null;
        }
        String label = previous.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    protected Categories categoriesDtoToCategories(CategoriesDto categoriesDto) {
        if ( categoriesDto == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setId( categoriesDto.getNextCatId() );
        categories.setLabel( categoriesDto.getNextCatLabel() );

        Categories target = doAfterMapping( categories );
        if ( target != null ) {
            return target;
        }

        return categories;
    }

    protected Categories categoriesDtoToCategories1(CategoriesDto categoriesDto) {
        if ( categoriesDto == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setId( categoriesDto.getPreviousCatId() );
        categories.setLabel( categoriesDto.getPreviousCatLabel() );

        Categories target = doAfterMapping( categories );
        if ( target != null ) {
            return target;
        }

        return categories;
    }
}
