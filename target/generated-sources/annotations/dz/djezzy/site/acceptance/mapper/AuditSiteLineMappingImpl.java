package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-31T16:21:05+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class AuditSiteLineMappingImpl implements AuditSiteLineMapping {

    @Autowired
    private CategoriesMapping categoriesMapping;

    @Override
    public List<AuditSiteLineDto> toDto(Collection<AuditSiteLine> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<AuditSiteLineDto> list = new ArrayList<AuditSiteLineDto>( sourceList.size() );
        for ( AuditSiteLine auditSiteLine : sourceList ) {
            list.add( toDto( auditSiteLine ) );
        }

        return list;
    }

    @Override
    public List<AuditSiteLine> toModel(Collection<AuditSiteLineDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<AuditSiteLine> list = new ArrayList<AuditSiteLine>( targetList.size() );
        for ( AuditSiteLineDto auditSiteLineDto : targetList ) {
            list.add( toModel( auditSiteLineDto ) );
        }

        return list;
    }

    @Override
    public AuditSiteLineDto toDto(AuditSiteLine source) {
        if ( source == null ) {
            return null;
        }

        AuditSiteLineDto auditSiteLineDto = new AuditSiteLineDto();

        auditSiteLineDto.setCategoriesLabel( sourceCategoriesLabel( source ) );
        auditSiteLineDto.setSecondDecisionLabel( sourceSecondDecisionLabel( source ) );
        auditSiteLineDto.setCategoriesId( sourceCategoriesId( source ) );
        auditSiteLineDto.setFirstDecisionLabel( sourceFirstDecisionLabel( source ) );
        auditSiteLineDto.setAuditSiteId( sourceAuditSiteId( source ) );
        auditSiteLineDto.setFirstDecisionId( sourceFirstDecisionId( source ) );
        auditSiteLineDto.setSecondDecisionId( sourceSecondDecisionId( source ) );
        auditSiteLineDto.setCreatedBy( source.getCreatedBy() );
        auditSiteLineDto.setModifiedBy( source.getModifiedBy() );
        auditSiteLineDto.setModificationDate( source.getModificationDate() );
        auditSiteLineDto.setId( source.getId() );
        auditSiteLineDto.setLabel( source.getLabel() );
        auditSiteLineDto.setSubCategoriesId( source.getSubCategoriesId() );
        auditSiteLineDto.setObservation( source.getObservation() );
        byte[] image = source.getImage();
        if ( image != null ) {
            auditSiteLineDto.setImage( Arrays.copyOf( image, image.length ) );
        }

        return auditSiteLineDto;
    }

    @Override
    public AuditSiteLine toModel(AuditSiteLineDto target) {
        if ( target == null ) {
            return null;
        }

        AuditSiteLine auditSiteLine = new AuditSiteLine();

        auditSiteLine.setFirstDecision( auditSiteLineDtoToDecision( target ) );
        auditSiteLine.setAuditSite( auditSiteLineDtoToAuditSite( target ) );
        auditSiteLine.setCategories( auditSiteLineDtoToCategories( target ) );
        auditSiteLine.setSecondDecision( auditSiteLineDtoToDecision1( target ) );
        auditSiteLine.setCreatedBy( target.getCreatedBy() );
        auditSiteLine.setModifiedBy( target.getModifiedBy() );
        auditSiteLine.setModificationDate( target.getModificationDate() );
        if ( target.getId() != null ) {
            auditSiteLine.setId( target.getId() );
        }
        auditSiteLine.setLabel( target.getLabel() );
        auditSiteLine.setSubCategoriesId( target.getSubCategoriesId() );
        auditSiteLine.setObservation( target.getObservation() );
        byte[] image = target.getImage();
        if ( image != null ) {
            auditSiteLine.setImage( Arrays.copyOf( image, image.length ) );
        }

        AuditSiteLine target1 = doAfterMapping( auditSiteLine );
        if ( target1 != null ) {
            return target1;
        }

        return auditSiteLine;
    }

    private String sourceCategoriesLabel(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        Categories categories = auditSiteLine.getCategories();
        if ( categories == null ) {
            return null;
        }
        String label = categories.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private String sourceSecondDecisionLabel(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        Decision secondDecision = auditSiteLine.getSecondDecision();
        if ( secondDecision == null ) {
            return null;
        }
        String label = secondDecision.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private Integer sourceCategoriesId(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        Categories categories = auditSiteLine.getCategories();
        if ( categories == null ) {
            return null;
        }
        Integer id = categories.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String sourceFirstDecisionLabel(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        Decision firstDecision = auditSiteLine.getFirstDecision();
        if ( firstDecision == null ) {
            return null;
        }
        String label = firstDecision.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private Integer sourceAuditSiteId(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        AuditSite auditSite = auditSiteLine.getAuditSite();
        if ( auditSite == null ) {
            return null;
        }
        Integer id = auditSite.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer sourceFirstDecisionId(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        Decision firstDecision = auditSiteLine.getFirstDecision();
        if ( firstDecision == null ) {
            return null;
        }
        Integer id = firstDecision.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer sourceSecondDecisionId(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        Decision secondDecision = auditSiteLine.getSecondDecision();
        if ( secondDecision == null ) {
            return null;
        }
        Integer id = secondDecision.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Decision auditSiteLineDtoToDecision(AuditSiteLineDto auditSiteLineDto) {
        if ( auditSiteLineDto == null ) {
            return null;
        }

        Decision decision = new Decision();

        decision.setId( auditSiteLineDto.getFirstDecisionId() );
        decision.setLabel( auditSiteLineDto.getFirstDecisionLabel() );

        return decision;
    }

    protected AuditSite auditSiteLineDtoToAuditSite(AuditSiteLineDto auditSiteLineDto) {
        if ( auditSiteLineDto == null ) {
            return null;
        }

        AuditSite auditSite = new AuditSite();

        auditSite.setId( auditSiteLineDto.getAuditSiteId() );

        return auditSite;
    }

    protected Categories auditSiteLineDtoToCategories(AuditSiteLineDto auditSiteLineDto) {
        if ( auditSiteLineDto == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setId( auditSiteLineDto.getCategoriesId() );
        categories.setLabel( auditSiteLineDto.getCategoriesLabel() );

        Categories target = categoriesMapping.doAfterMapping( categories );
        if ( target != null ) {
            return target;
        }

        return categories;
    }

    protected Decision auditSiteLineDtoToDecision1(AuditSiteLineDto auditSiteLineDto) {
        if ( auditSiteLineDto == null ) {
            return null;
        }

        Decision decision = new Decision();

        decision.setId( auditSiteLineDto.getSecondDecisionId() );
        decision.setLabel( auditSiteLineDto.getSecondDecisionLabel() );

        return decision;
    }
}
