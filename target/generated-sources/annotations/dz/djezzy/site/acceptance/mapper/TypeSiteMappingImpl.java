package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.TypeSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.TypeSite;
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
public class TypeSiteMappingImpl implements TypeSiteMapping {

    @Override
    public TypeSiteDto toDto(TypeSite source) {
        if ( source == null ) {
            return null;
        }

        TypeSiteDto typeSiteDto = new TypeSiteDto();

        typeSiteDto.setId( source.getId() );
        typeSiteDto.setTypeDetailEquip( source.getTypeDetailEquip() );
        typeSiteDto.setTypeInstallation( source.getTypeInstallation() );
        typeSiteDto.setTypeSiteLib( source.getTypeSiteLib() );

        return typeSiteDto;
    }

    @Override
    public TypeSite toModel(TypeSiteDto target) {
        if ( target == null ) {
            return null;
        }

        TypeSite typeSite = new TypeSite();

        typeSite.setId( target.getId() );
        typeSite.setTypeDetailEquip( target.getTypeDetailEquip() );
        typeSite.setTypeInstallation( target.getTypeInstallation() );
        typeSite.setTypeSiteLib( target.getTypeSiteLib() );

        return typeSite;
    }

    @Override
    public List<TypeSiteDto> toDto(Collection<TypeSite> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<TypeSiteDto> list = new ArrayList<TypeSiteDto>( sourceList.size() );
        for ( TypeSite typeSite : sourceList ) {
            list.add( toDto( typeSite ) );
        }

        return list;
    }

    @Override
    public List<TypeSite> toModel(Collection<TypeSiteDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<TypeSite> list = new ArrayList<TypeSite>( targetList.size() );
        for ( TypeSiteDto typeSiteDto : targetList ) {
            list.add( toModel( typeSiteDto ) );
        }

        return list;
    }
}
