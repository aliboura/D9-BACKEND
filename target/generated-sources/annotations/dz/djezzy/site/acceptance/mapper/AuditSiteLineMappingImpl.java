package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.dto.SubCategoriesDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.business.data.entities.Categories;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.data.entities.SubCategories;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-05T09:45:07+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class AuditSiteLineMappingImpl implements AuditSiteLineMapping {

    @Autowired
    private CategoriesMapping categoriesMapping;
    @Autowired
    private SubCategoriesMapping subCategoriesMapping;

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
        auditSiteLineDto.setSubCategoriesLabel( sourceSubCategoriesLabel( source ) );
        auditSiteLineDto.setSubCategoriesId( sourceSubCategoriesId( source ) );
        auditSiteLineDto.setCategoriesId( sourceCategoriesId( source ) );
        auditSiteLineDto.setFirstDecisionLabel( sourceFirstDecisionLabel( source ) );
        auditSiteLineDto.setSubCategories( subCategoriesMapping.toDto( source.getSubCategories() ) );
        auditSiteLineDto.setSubCategoriesValueType( sourceSubCategoriesValueType( source ) );
        auditSiteLineDto.setAuditSiteId( sourceAuditSiteId( source ) );
        auditSiteLineDto.setFirstDecisionId( sourceFirstDecisionId( source ) );
        auditSiteLineDto.setSecondDecisionId( sourceSecondDecisionId( source ) );
        auditSiteLineDto.setCreatedBy( source.getCreatedBy() );
        auditSiteLineDto.setModifiedBy( source.getModifiedBy() );
        auditSiteLineDto.setModificationDate( source.getModificationDate() );
        auditSiteLineDto.setId( source.getId() );
        auditSiteLineDto.setLabel( source.getLabel() );
        auditSiteLineDto.setBlocking( source.isBlocking() );
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

        auditSiteLine.setSubCategories( auditSiteLineDtoToSubCategories( target ) );
        auditSiteLine.setFirstDecision( auditSiteLineDtoToDecision( target ) );
        auditSiteLine.setAuditSite( auditSiteLineDtoToAuditSite( target ) );
        if ( target.getSubCategories() != null ) {
            if ( auditSiteLine.getCategories() == null ) {
                auditSiteLine.setCategories( new Categories() );
            }
            subCategoriesDtoToCategories( target.getSubCategories(), auditSiteLine.getCategories() );
        }
        if ( auditSiteLine.getCategories() == null ) {
            auditSiteLine.setCategories( new Categories() );
        }
        auditSiteLineDtoToCategories( target, auditSiteLine.getCategories() );
        auditSiteLine.setSecondDecision( auditSiteLineDtoToDecision1( target ) );
        auditSiteLine.setCreatedBy( target.getCreatedBy() );
        auditSiteLine.setModifiedBy( target.getModifiedBy() );
        auditSiteLine.setModificationDate( target.getModificationDate() );
        if ( target.getId() != null ) {
            auditSiteLine.setId( target.getId() );
        }
        auditSiteLine.setLabel( target.getLabel() );
        auditSiteLine.setBlocking( target.isBlocking() );
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

    private String sourceSubCategoriesLabel(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        SubCategories subCategories = auditSiteLine.getSubCategories();
        if ( subCategories == null ) {
            return null;
        }
        String label = subCategories.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private Integer sourceSubCategoriesId(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        SubCategories subCategories = auditSiteLine.getSubCategories();
        if ( subCategories == null ) {
            return null;
        }
        int id = subCategories.getId();
        return id;
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

    private Integer sourceSubCategoriesValueType(AuditSiteLine auditSiteLine) {
        if ( auditSiteLine == null ) {
            return null;
        }
        SubCategories subCategories = auditSiteLine.getSubCategories();
        if ( subCategories == null ) {
            return null;
        }
        Integer valueType = subCategories.getValueType();
        if ( valueType == null ) {
            return null;
        }
        return valueType;
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

    protected SubCategories auditSiteLineDtoToSubCategories(AuditSiteLineDto auditSiteLineDto) {
        if ( auditSiteLineDto == null ) {
            return null;
        }

        SubCategories subCategories = new SubCategories();

        if ( auditSiteLineDto.getSubCategoriesId() != null ) {
            subCategories.setId( auditSiteLineDto.getSubCategoriesId() );
        }
        subCategories.setValueType( auditSiteLineDto.getSubCategoriesValueType() );
        subCategories.setLabel( auditSiteLineDto.getSubCategoriesLabel() );

        return subCategories;
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

    protected void subCategoriesDtoToCategories(SubCategoriesDto subCategoriesDto, Categories mappingTarget) {
        if ( subCategoriesDto == null ) {
            return;
        }

        mappingTarget.setId( subCategoriesDto.getId() );
        mappingTarget.setLabel( subCategoriesDto.getLabel() );
        mappingTarget.setPosition( subCategoriesDto.getPosition() );
        mappingTarget.setStatus( subCategoriesDto.isStatus() );

        Categories target = categoriesMapping.doAfterMapping( mappingTarget );
        if ( target != null ) {
            return;
        }
    }

    protected void auditSiteLineDtoToCategories(AuditSiteLineDto auditSiteLineDto, Categories mappingTarget) {
        if ( auditSiteLineDto == null ) {
            return;
        }

        mappingTarget.setId( auditSiteLineDto.getCategoriesId() );
        mappingTarget.setLabel( auditSiteLineDto.getCategoriesLabel() );

        Categories target = categoriesMapping.doAfterMapping( mappingTarget );
        if ( target != null ) {
            return;
        }
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
