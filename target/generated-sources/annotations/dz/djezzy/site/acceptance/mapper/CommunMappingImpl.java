package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.CommuneDto;
import dz.djezzy.site.acceptance.business.data.entities.Commune;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-08T17:50:38+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class CommunMappingImpl implements CommunMapping {

    @Override
    public List<CommuneDto> toDto(Collection<Commune> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<CommuneDto> list = new ArrayList<CommuneDto>( sourceList.size() );
        for ( Commune commune : sourceList ) {
            list.add( toDto( commune ) );
        }

        return list;
    }

    @Override
    public List<Commune> toModel(Collection<CommuneDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<Commune> list = new ArrayList<Commune>( targetList.size() );
        for ( CommuneDto communeDto : targetList ) {
            list.add( toModel( communeDto ) );
        }

        return list;
    }

    @Override
    public CommuneDto toDto(Commune source) {
        if ( source == null ) {
            return null;
        }

        CommuneDto communeDto = new CommuneDto();

        communeDto.setWilayaLabel( sourceWilayaLabel( source ) );
        communeDto.setWilayaId( sourceWilayaId( source ) );
        communeDto.setId( source.getId() );
        communeDto.setLabel( source.getLabel() );

        return communeDto;
    }

    @Override
    public Commune toModel(CommuneDto target) {
        if ( target == null ) {
            return null;
        }

        Commune commune = new Commune();

        commune.setWilaya( communeDtoToWilaya( target ) );
        commune.setId( target.getId() );
        commune.setLabel( target.getLabel() );

        return commune;
    }

    private String sourceWilayaLabel(Commune commune) {
        if ( commune == null ) {
            return null;
        }
        Wilaya wilaya = commune.getWilaya();
        if ( wilaya == null ) {
            return null;
        }
        String label = wilaya.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private int sourceWilayaId(Commune commune) {
        if ( commune == null ) {
            return 0;
        }
        Wilaya wilaya = commune.getWilaya();
        if ( wilaya == null ) {
            return 0;
        }
        int id = wilaya.getId();
        return id;
    }

    protected Wilaya communeDtoToWilaya(CommuneDto communeDto) {
        if ( communeDto == null ) {
            return null;
        }

        Wilaya wilaya = new Wilaya();

        wilaya.setLabel( communeDto.getWilayaLabel() );
        wilaya.setId( communeDto.getWilayaId() );

        return wilaya;
    }
}
