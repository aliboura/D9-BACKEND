package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.Status;
import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-04T16:41:35+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class StatusAuditSiteMappingImpl implements StatusAuditSiteMapping {

    @Override
    public List<StatusAuditSiteDto> toDto(Collection<StatusAuditSite> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<StatusAuditSiteDto> list = new ArrayList<StatusAuditSiteDto>( sourceList.size() );
        for ( StatusAuditSite statusAuditSite : sourceList ) {
            list.add( toDto( statusAuditSite ) );
        }

        return list;
    }

    @Override
    public List<StatusAuditSite> toModel(Collection<StatusAuditSiteDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<StatusAuditSite> list = new ArrayList<StatusAuditSite>( targetList.size() );
        for ( StatusAuditSiteDto statusAuditSiteDto : targetList ) {
            list.add( toModel( statusAuditSiteDto ) );
        }

        return list;
    }

    @Override
    public StatusAuditSiteDto toDto(StatusAuditSite source) {
        if ( source == null ) {
            return null;
        }

        StatusAuditSiteDto statusAuditSiteDto = new StatusAuditSiteDto();

        Long id = sourceStatusId( source );
        if ( id != null ) {
            statusAuditSiteDto.setStatusId( id );
        }
        statusAuditSiteDto.setStatusLabel( sourceStatusLabel( source ) );
        statusAuditSiteDto.setAuditSiteId( sourceAuditSiteId( source ) );
        statusAuditSiteDto.setCreatedBy( source.getCreatedBy() );
        statusAuditSiteDto.setModifiedBy( source.getModifiedBy() );
        statusAuditSiteDto.setModificationDate( source.getModificationDate() );
        statusAuditSiteDto.setId( source.getId() );
        statusAuditSiteDto.setStatusDate( source.getStatusDate() );
        statusAuditSiteDto.setDescription( source.getDescription() );
        statusAuditSiteDto.setCurrent( source.getCurrent() );

        return statusAuditSiteDto;
    }

    @Override
    public StatusAuditSite toModel(StatusAuditSiteDto target) {
        if ( target == null ) {
            return null;
        }

        StatusAuditSite statusAuditSite = new StatusAuditSite();

        statusAuditSite.setStatus( statusAuditSiteDtoToStatus( target ) );
        statusAuditSite.setAuditSite( statusAuditSiteDtoToAuditSite( target ) );
        statusAuditSite.setCreatedBy( target.getCreatedBy() );
        statusAuditSite.setModifiedBy( target.getModifiedBy() );
        statusAuditSite.setModificationDate( target.getModificationDate() );
        statusAuditSite.setId( target.getId() );
        statusAuditSite.setStatusDate( target.getStatusDate() );
        statusAuditSite.setDescription( target.getDescription() );
        statusAuditSite.setCurrent( target.getCurrent() );

        return statusAuditSite;
    }

    private Long sourceStatusId(StatusAuditSite statusAuditSite) {
        if ( statusAuditSite == null ) {
            return null;
        }
        Status status = statusAuditSite.getStatus();
        if ( status == null ) {
            return null;
        }
        Long id = status.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String sourceStatusLabel(StatusAuditSite statusAuditSite) {
        if ( statusAuditSite == null ) {
            return null;
        }
        Status status = statusAuditSite.getStatus();
        if ( status == null ) {
            return null;
        }
        String label = status.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private Long sourceAuditSiteId(StatusAuditSite statusAuditSite) {
        if ( statusAuditSite == null ) {
            return null;
        }
        AuditSite auditSite = statusAuditSite.getAuditSite();
        if ( auditSite == null ) {
            return null;
        }
        Long id = auditSite.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Status statusAuditSiteDtoToStatus(StatusAuditSiteDto statusAuditSiteDto) {
        if ( statusAuditSiteDto == null ) {
            return null;
        }

        Status status = new Status();

        status.setLabel( statusAuditSiteDto.getStatusLabel() );
        status.setId( statusAuditSiteDto.getStatusId() );

        return status;
    }

    protected AuditSite statusAuditSiteDtoToAuditSite(StatusAuditSiteDto statusAuditSiteDto) {
        if ( statusAuditSiteDto == null ) {
            return null;
        }

        AuditSite auditSite = new AuditSite();

        auditSite.setId( statusAuditSiteDto.getAuditSiteId() );

        return auditSite;
    }
}
