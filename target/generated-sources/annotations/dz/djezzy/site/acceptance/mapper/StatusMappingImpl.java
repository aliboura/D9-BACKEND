package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-16T09:44:55+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class StatusMappingImpl implements StatusMapping {

    @Override
    public List<StatusDto> toDto(Collection<Status> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<StatusDto> list = new ArrayList<StatusDto>( sourceList.size() );
        for ( Status status : sourceList ) {
            list.add( toDto( status ) );
        }

        return list;
    }

    @Override
    public List<Status> toModel(Collection<StatusDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<Status> list = new ArrayList<Status>( targetList.size() );
        for ( StatusDto statusDto : targetList ) {
            list.add( toModel( statusDto ) );
        }

        return list;
    }

    @Override
    public StatusDto toDto(Status source) {
        if ( source == null ) {
            return null;
        }

        StatusDto statusDto = new StatusDto();

        statusDto.setId( source.getId() );
        statusDto.setLabel( source.getLabel() );
        statusDto.setDescription( source.getDescription() );
        statusDto.setStyleCSS( source.getStyleCSS() );
        statusDto.setMotif( source.isMotif() );

        return statusDto;
    }

    @Override
    public Status toModel(StatusDto target) {
        if ( target == null ) {
            return null;
        }

        Status status = new Status();

        status.setId( target.getId() );
        status.setLabel( target.getLabel() );
        status.setDescription( target.getDescription() );
        status.setStyleCSS( target.getStyleCSS() );
        status.setMotif( target.isMotif() );

        return status;
    }
}
