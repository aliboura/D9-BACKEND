package dz.djezzy.site.acceptance.mapper;

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
    date = "2020-04-26T10:53:28+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class SubCategoriesMappingImpl implements SubCategoriesMapping {

    @Override
    public List<SubCategoriesDto> toDto(Collection<SubCategories> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<SubCategoriesDto> list = new ArrayList<SubCategoriesDto>( sourceList.size() );
        for ( SubCategories subCategories : sourceList ) {
            list.add( toDto( subCategories ) );
        }

        return list;
    }

    @Override
    public List<SubCategories> toModel(Collection<SubCategoriesDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<SubCategories> list = new ArrayList<SubCategories>( targetList.size() );
        for ( SubCategoriesDto subCategoriesDto : targetList ) {
            list.add( toModel( subCategoriesDto ) );
        }

        return list;
    }

    @Override
    public SubCategoriesDto toDto(SubCategories source) {
        if ( source == null ) {
            return null;
        }

        SubCategoriesDto subCategoriesDto = new SubCategoriesDto();

        subCategoriesDto.setCategoriesLabel( sourceCategoriesLabel( source ) );
        Integer id = sourceCategoriesId( source );
        if ( id != null ) {
            subCategoriesDto.setCategoriesId( id );
        }
        subCategoriesDto.setId( source.getId() );
        subCategoriesDto.setLabel( source.getLabel() );
        subCategoriesDto.setPosition( source.getPosition() );
        subCategoriesDto.setValueType( source.getValueType() );
        subCategoriesDto.setStatus( source.isStatus() );
        subCategoriesDto.setBlocking( source.isBlocking() );

        return subCategoriesDto;
    }

    @Override
    public SubCategories toModel(SubCategoriesDto target) {
        if ( target == null ) {
            return null;
        }

        SubCategories subCategories = new SubCategories();

        subCategories.setCategories( subCategoriesDtoToCategories( target ) );
        subCategories.setId( target.getId() );
        subCategories.setLabel( target.getLabel() );
        subCategories.setPosition( target.getPosition() );
        subCategories.setValueType( target.getValueType() );
        subCategories.setStatus( target.isStatus() );
        subCategories.setBlocking( target.isBlocking() );

        return subCategories;
    }

    private String sourceCategoriesLabel(SubCategories subCategories) {
        if ( subCategories == null ) {
            return null;
        }
        Categories categories = subCategories.getCategories();
        if ( categories == null ) {
            return null;
        }
        String label = categories.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private Integer sourceCategoriesId(SubCategories subCategories) {
        if ( subCategories == null ) {
            return null;
        }
        Categories categories = subCategories.getCategories();
        if ( categories == null ) {
            return null;
        }
        Integer id = categories.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Categories subCategoriesDtoToCategories(SubCategoriesDto subCategoriesDto) {
        if ( subCategoriesDto == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setId( subCategoriesDto.getCategoriesId() );
        categories.setLabel( subCategoriesDto.getCategoriesLabel() );

        return categories;
    }
}
