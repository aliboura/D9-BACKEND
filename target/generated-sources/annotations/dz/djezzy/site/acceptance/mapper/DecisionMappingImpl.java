package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.DecisionDto;
import dz.djezzy.site.acceptance.business.data.entities.Decision;
import dz.djezzy.site.acceptance.business.data.entities.DecisionType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-08T17:50:37+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class DecisionMappingImpl implements DecisionMapping {

    @Override
    public List<DecisionDto> toDto(Collection<Decision> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<DecisionDto> list = new ArrayList<DecisionDto>( sourceList.size() );
        for ( Decision decision : sourceList ) {
            list.add( toDto( decision ) );
        }

        return list;
    }

    @Override
    public List<Decision> toModel(Collection<DecisionDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<Decision> list = new ArrayList<Decision>( targetList.size() );
        for ( DecisionDto decisionDto : targetList ) {
            list.add( toModel( decisionDto ) );
        }

        return list;
    }

    @Override
    public DecisionDto toDto(Decision source) {
        if ( source == null ) {
            return null;
        }

        DecisionDto decisionDto = new DecisionDto();

        decisionDto.setDecisionTypeId( sourceDecisionTypeId( source ) );
        decisionDto.setDecisionTypeLabel( sourceDecisionTypeLabel( source ) );
        decisionDto.setId( source.getId() );
        decisionDto.setLabel( source.getLabel() );
        decisionDto.setPosition( source.getPosition() );
        decisionDto.setStatus( source.getStatus() );
        decisionDto.setClosed( source.getClosed() );

        return decisionDto;
    }

    @Override
    public Decision toModel(DecisionDto target) {
        if ( target == null ) {
            return null;
        }

        Decision decision = new Decision();

        decision.setDecisionType( decisionDtoToDecisionType( target ) );
        decision.setId( target.getId() );
        decision.setLabel( target.getLabel() );
        decision.setPosition( target.getPosition() );
        decision.setStatus( target.getStatus() );
        decision.setClosed( target.getClosed() );

        return decision;
    }

    private Integer sourceDecisionTypeId(Decision decision) {
        if ( decision == null ) {
            return null;
        }
        DecisionType decisionType = decision.getDecisionType();
        if ( decisionType == null ) {
            return null;
        }
        Integer id = decisionType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String sourceDecisionTypeLabel(Decision decision) {
        if ( decision == null ) {
            return null;
        }
        DecisionType decisionType = decision.getDecisionType();
        if ( decisionType == null ) {
            return null;
        }
        String label = decisionType.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    protected DecisionType decisionDtoToDecisionType(DecisionDto decisionDto) {
        if ( decisionDto == null ) {
            return null;
        }

        DecisionType decisionType = new DecisionType();

        decisionType.setLabel( decisionDto.getDecisionTypeLabel() );
        decisionType.setId( decisionDto.getDecisionTypeId() );

        return decisionType;
    }
}
