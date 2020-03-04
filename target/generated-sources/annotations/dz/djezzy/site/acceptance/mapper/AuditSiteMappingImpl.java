package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.data.entities.Status;
import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AuditSiteMappingImpl implements AuditSiteMapping {

    @Override
    public List<AuditSiteDto> toDto(Collection<AuditSite> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<AuditSiteDto> list = new ArrayList<AuditSiteDto>( sourceList.size() );
        for ( AuditSite auditSite : sourceList ) {
            list.add( toDto( auditSite ) );
        }

        return list;
    }

    @Override
    public List<AuditSite> toModel(Collection<AuditSiteDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<AuditSite> list = new ArrayList<AuditSite>( targetList.size() );
        for ( AuditSiteDto auditSiteDto : targetList ) {
            list.add( toModel( auditSiteDto ) );
        }

        return list;
    }

    @Override
    public AuditSiteDto toDto(AuditSite source) {
        if ( source == null ) {
            return null;
        }

        AuditSiteDto auditSiteDto = new AuditSiteDto();

        auditSiteDto.setCurrentCategoriesLabel( sourceCurrentCategoriesLabel( source ) );
        auditSiteDto.setSecondDecisionLabel( sourceSecondDecisionLabel( source ) );
        auditSiteDto.setStatusAuditSitesDtoList( statusAuditSiteListToStatusAuditSiteDtoList( source.getStatusAuditSitesList() ) );
        auditSiteDto.setAuditSiteLineDtoList( auditSiteLineListToAuditSiteLineDtoList( source.getAuditLineList() ) );
        auditSiteDto.setFirstDecisionLabel( sourceFirstDecisionLabel( source ) );
        auditSiteDto.setCurrentSatusLabel( sourceCurrentSatusLabel( source ) );
        auditSiteDto.setCurrentSatusId( sourceCurrentSatusId( source ) );
        auditSiteDto.setCurrentCategoriesId( sourceCurrentCategoriesId( source ) );
        auditSiteDto.setFirstDecisionId( sourceFirstDecisionId( source ) );
        auditSiteDto.setSecondDecisionId( sourceSecondDecisionId( source ) );
        auditSiteDto.setCreatedBy( source.getCreatedBy() );
        auditSiteDto.setModifiedBy( source.getModifiedBy() );
        auditSiteDto.setModificationDate( source.getModificationDate() );
        auditSiteDto.setId( source.getId() );
        auditSiteDto.setAuditDate( source.getAuditDate() );
        auditSiteDto.setUserId( source.getUserId() );
        auditSiteDto.setSiteCode( source.getSiteCode() );
        auditSiteDto.setWilayaId( source.getWilayaId() );
        auditSiteDto.setRegionId( source.getRegionId() );
        auditSiteDto.setRepOwner( source.getRepOwner() );
        auditSiteDto.setDescription( source.getDescription() );
        auditSiteDto.setObservation( source.getObservation() );
        auditSiteDto.setFirstDecisionDate( source.getFirstDecisionDate() );
        auditSiteDto.setFirstDecisionEngineerSite( source.getFirstDecisionEngineerSite() );
        auditSiteDto.setFirstDecisionEngineerOM( source.getFirstDecisionEngineerOM() );
        auditSiteDto.setSecondDecisionDate( source.getSecondDecisionDate() );
        auditSiteDto.setSecondDecisionEngineerSite( source.getSecondDecisionEngineerSite() );
        auditSiteDto.setSecondDecisionEngineerOM( source.getSecondDecisionEngineerOM() );

        return auditSiteDto;
    }

    @Override
    public AuditSite toModel(AuditSiteDto target) {
        if ( target == null ) {
            return null;
        }

        AuditSite auditSite = new AuditSite();

        auditSite.setCurrentSatus( auditSiteDtoToStatus( target ) );
        auditSite.setFirstDecision( auditSiteDtoToDecision( target ) );
        auditSite.setCurrentCategories( auditSiteDtoToCategories( target ) );
        auditSite.setSecondDecision( auditSiteDtoToDecision1( target ) );
        auditSite.setAuditLineList( auditSiteLineDtoListToAuditSiteLineList( target.getAuditSiteLineDtoList() ) );
        auditSite.setStatusAuditSitesList( statusAuditSiteDtoListToStatusAuditSiteList( target.getStatusAuditSitesDtoList() ) );
        auditSite.setCreatedBy( target.getCreatedBy() );
        auditSite.setModifiedBy( target.getModifiedBy() );
        auditSite.setModificationDate( target.getModificationDate() );
        auditSite.setId( target.getId() );
        auditSite.setAuditDate( target.getAuditDate() );
        auditSite.setUserId( target.getUserId() );
        auditSite.setSiteCode( target.getSiteCode() );
        auditSite.setWilayaId( target.getWilayaId() );
        auditSite.setRegionId( target.getRegionId() );
        auditSite.setRepOwner( target.getRepOwner() );
        auditSite.setDescription( target.getDescription() );
        auditSite.setObservation( target.getObservation() );
        auditSite.setFirstDecisionDate( target.getFirstDecisionDate() );
        auditSite.setFirstDecisionEngineerSite( target.getFirstDecisionEngineerSite() );
        auditSite.setFirstDecisionEngineerOM( target.getFirstDecisionEngineerOM() );
        auditSite.setSecondDecisionDate( target.getSecondDecisionDate() );
        auditSite.setSecondDecisionEngineerSite( target.getSecondDecisionEngineerSite() );
        auditSite.setSecondDecisionEngineerOM( target.getSecondDecisionEngineerOM() );

        return auditSite;
    }

    private String sourceCurrentCategoriesLabel(AuditSite auditSite) {
        if ( auditSite == null ) {
            return null;
        }
        Categories currentCategories = auditSite.getCurrentCategories();
        if ( currentCategories == null ) {
            return null;
        }
        String label = currentCategories.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private String sourceSecondDecisionLabel(AuditSite auditSite) {
        if ( auditSite == null ) {
            return null;
        }
        Decision secondDecision = auditSite.getSecondDecision();
        if ( secondDecision == null ) {
            return null;
        }
        String label = secondDecision.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    protected StatusAuditSiteDto statusAuditSiteToStatusAuditSiteDto(StatusAuditSite statusAuditSite) {
        if ( statusAuditSite == null ) {
            return null;
        }

        StatusAuditSiteDto statusAuditSiteDto = new StatusAuditSiteDto();

        statusAuditSiteDto.setCreatedBy( statusAuditSite.getCreatedBy() );
        statusAuditSiteDto.setModifiedBy( statusAuditSite.getModifiedBy() );
        statusAuditSiteDto.setModificationDate( statusAuditSite.getModificationDate() );
        statusAuditSiteDto.setId( statusAuditSite.getId() );
        statusAuditSiteDto.setStatusDate( statusAuditSite.getStatusDate() );
        statusAuditSiteDto.setDescription( statusAuditSite.getDescription() );
        statusAuditSiteDto.setCurrent( statusAuditSite.getCurrent() );

        return statusAuditSiteDto;
    }

    protected List<StatusAuditSiteDto> statusAuditSiteListToStatusAuditSiteDtoList(List<StatusAuditSite> list) {
        if ( list == null ) {
            return null;
        }

        List<StatusAuditSiteDto> list1 = new ArrayList<StatusAuditSiteDto>( list.size() );
        for ( StatusAuditSite statusAuditSite : list ) {
            list1.add( statusAuditSiteToStatusAuditSiteDto( statusAuditSite ) );
        }

        return list1;
    }

    protected AuditSiteLineDto auditSiteLineToAuditSiteLineDto(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }

        AuditSiteLineDto auditSiteLineDto = new AuditSiteLineDto();

        auditSiteLineDto.setCreatedBy( auditSiteLine.getCreatedBy() );
        auditSiteLineDto.setModifiedBy( auditSiteLine.getModifiedBy() );
        auditSiteLineDto.setModificationDate( auditSiteLine.getModificationDate() );
        auditSiteLineDto.setId( auditSiteLine.getId() );
        auditSiteLineDto.setObservation( auditSiteLine.getObservation() );
        byte[] image = auditSiteLine.getImage();
        if ( image != null ) {
            auditSiteLineDto.setImage( Arrays.copyOf( image, image.length ) );
        }

        return auditSiteLineDto;
    }

    protected List<AuditSiteLineDto> auditSiteLineListToAuditSiteLineDtoList(List<AuditSiteLine> list) {
        if ( list == null ) {
            return null;
        }

        List<AuditSiteLineDto> list1 = new ArrayList<AuditSiteLineDto>( list.size() );
        for ( AuditSiteLine auditSiteLine : list ) {
            list1.add( auditSiteLineToAuditSiteLineDto( auditSiteLine ) );
        }

        return list1;
    }

    private String sourceFirstDecisionLabel(AuditSite auditSite) {
        if ( auditSite == null ) {
            return null;
        }
        Decision firstDecision = auditSite.getFirstDecision();
        if ( firstDecision == null ) {
            return null;
        }
        String label = firstDecision.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private String sourceCurrentSatusLabel(AuditSite auditSite) {
        if ( auditSite == null ) {
            return null;
        }
        Status currentSatus = auditSite.getCurrentSatus();
        if ( currentSatus == null ) {
            return null;
        }
        String label = currentSatus.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private Long sourceCurrentSatusId(AuditSite auditSite) {
        if ( auditSite == null ) {
            return null;
        }
        Status currentSatus = auditSite.getCurrentSatus();
        if ( currentSatus == null ) {
            return null;
        }
        Long id = currentSatus.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long sourceCurrentCategoriesId(AuditSite auditSite) {
        if ( auditSite == null ) {
            return null;
        }
        Categories currentCategories = auditSite.getCurrentCategories();
        if ( currentCategories == null ) {
            return null;
        }
        Long id = currentCategories.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private int sourceFirstDecisionId(AuditSite auditSite) {
        if ( auditSite == null ) {
            return 0;
        }
        Decision firstDecision = auditSite.getFirstDecision();
        if ( firstDecision == null ) {
            return 0;
        }
        int id = firstDecision.getId();
        return id;
    }

    private int sourceSecondDecisionId(AuditSite auditSite) {
        if ( auditSite == null ) {
            return 0;
        }
        Decision secondDecision = auditSite.getSecondDecision();
        if ( secondDecision == null ) {
            return 0;
        }
        int id = secondDecision.getId();
        return id;
    }

    protected Status auditSiteDtoToStatus(AuditSiteDto auditSiteDto) {
        if ( auditSiteDto == null ) {
            return null;
        }

        Status status = new Status();

        status.setLabel( auditSiteDto.getCurrentSatusLabel() );
        status.setId( auditSiteDto.getCurrentSatusId() );

        return status;
    }

    protected Decision auditSiteDtoToDecision(AuditSiteDto auditSiteDto) {
        if ( auditSiteDto == null ) {
            return null;
        }

        Decision decision = new Decision();

        decision.setId( auditSiteDto.getFirstDecisionId() );
        decision.setLabel( auditSiteDto.getFirstDecisionLabel() );

        return decision;
    }

    protected Categories auditSiteDtoToCategories(AuditSiteDto auditSiteDto) {
        if ( auditSiteDto == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setLabel( auditSiteDto.getCurrentCategoriesLabel() );
        categories.setId( auditSiteDto.getCurrentCategoriesId() );

        return categories;
    }

    protected Decision auditSiteDtoToDecision1(AuditSiteDto auditSiteDto) {
        if ( auditSiteDto == null ) {
            return null;
        }

        Decision decision = new Decision();

        decision.setId( auditSiteDto.getSecondDecisionId() );
        decision.setLabel( auditSiteDto.getSecondDecisionLabel() );

        return decision;
    }

    protected AuditSiteLine auditSiteLineDtoToAuditSiteLine(AuditSiteLineDto auditSiteLineDto) {
        if ( auditSiteLineDto == null ) {
            return null;
        }

        AuditSiteLine auditSiteLine = new AuditSiteLine();

        auditSiteLine.setCreatedBy( auditSiteLineDto.getCreatedBy() );
        auditSiteLine.setModifiedBy( auditSiteLineDto.getModifiedBy() );
        auditSiteLine.setModificationDate( auditSiteLineDto.getModificationDate() );
        auditSiteLine.setId( auditSiteLineDto.getId() );
        auditSiteLine.setObservation( auditSiteLineDto.getObservation() );
        byte[] image = auditSiteLineDto.getImage();
        if ( image != null ) {
            auditSiteLine.setImage( Arrays.copyOf( image, image.length ) );
        }

        return auditSiteLine;
    }

    protected List<AuditSiteLine> auditSiteLineDtoListToAuditSiteLineList(List<AuditSiteLineDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AuditSiteLine> list1 = new ArrayList<AuditSiteLine>( list.size() );
        for ( AuditSiteLineDto auditSiteLineDto : list ) {
            list1.add( auditSiteLineDtoToAuditSiteLine( auditSiteLineDto ) );
        }

        return list1;
    }

    protected StatusAuditSite statusAuditSiteDtoToStatusAuditSite(StatusAuditSiteDto statusAuditSiteDto) {
        if ( statusAuditSiteDto == null ) {
            return null;
        }

        StatusAuditSite statusAuditSite = new StatusAuditSite();

        statusAuditSite.setCreatedBy( statusAuditSiteDto.getCreatedBy() );
        statusAuditSite.setModifiedBy( statusAuditSiteDto.getModifiedBy() );
        statusAuditSite.setModificationDate( statusAuditSiteDto.getModificationDate() );
        statusAuditSite.setId( statusAuditSiteDto.getId() );
        statusAuditSite.setStatusDate( statusAuditSiteDto.getStatusDate() );
        statusAuditSite.setDescription( statusAuditSiteDto.getDescription() );
        statusAuditSite.setCurrent( statusAuditSiteDto.getCurrent() );

        return statusAuditSite;
    }

    protected List<StatusAuditSite> statusAuditSiteDtoListToStatusAuditSiteList(List<StatusAuditSiteDto> list) {
        if ( list == null ) {
            return null;
        }

        List<StatusAuditSite> list1 = new ArrayList<StatusAuditSite>( list.size() );
        for ( StatusAuditSiteDto statusAuditSiteDto : list ) {
            list1.add( statusAuditSiteDtoToStatusAuditSite( statusAuditSiteDto ) );
        }

        return list1;
    }
}
