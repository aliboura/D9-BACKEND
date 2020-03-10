package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.WilayaDto;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
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
public class WilayaMappingImpl implements WilayaMapping {

    @Override
    public List<WilayaDto> toDto(Collection<Wilaya> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<WilayaDto> list = new ArrayList<WilayaDto>( sourceList.size() );
        for ( Wilaya wilaya : sourceList ) {
            list.add( toDto( wilaya ) );
        }

        return list;
    }

    @Override
    public List<Wilaya> toModel(Collection<WilayaDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<Wilaya> list = new ArrayList<Wilaya>( targetList.size() );
        for ( WilayaDto wilayaDto : targetList ) {
            list.add( toModel( wilayaDto ) );
        }

        return list;
    }

    @Override
    public WilayaDto toDto(Wilaya source) {
        if ( source == null ) {
            return null;
        }

        WilayaDto wilayaDto = new WilayaDto();

        wilayaDto.setId( source.getId() );
        wilayaDto.setLabel( source.getLabel() );
        wilayaDto.setRegionId( source.getRegionId() );

        return wilayaDto;
    }

    @Override
    public Wilaya toModel(WilayaDto target) {
        if ( target == null ) {
            return null;
        }

        Wilaya wilaya = new Wilaya();

        wilaya.setId( target.getId() );
        wilaya.setLabel( target.getLabel() );
        wilaya.setRegionId( target.getRegionId() );

        return wilaya;
    }
}
