package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteLineDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSiteLine;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditSiteLineMapping extends GenericMapper<AuditSiteLine, AuditSiteLineDto> {

    AuditSiteLineMapping INSTANCE = Mappers.getMapper(AuditSiteLineMapping.class);

    @Mappings({
            @Mapping(source = "auditSite.id", target = "auditSiteId"),
            @Mapping(source = "firstDecision.id", target = "firstDecisionId"),
            @Mapping(source = "firstDecision.label", target = "firstDecisionLabel"),
            @Mapping(source = "secondDecision.id", target = "secondDecisionId"),
            @Mapping(source = "secondDecision.label", target = "secondDecisionLabel")
    })
    @Override
    AuditSiteLineDto toDto(AuditSiteLine source);

    @Mappings({
            @Mapping(target = "auditSite.id", source = "auditSiteId"),
            @Mapping(target = "firstDecision.id", source = "firstDecisionId"),
            @Mapping(target = "firstDecision.label", source = "firstDecisionLabel"),
            @Mapping(target = "secondDecision.id", source = "secondDecisionId"),
            @Mapping(target = "secondDecision.label", source = "secondDecisionLabel")
    })
    @Override
    AuditSiteLine toModel(AuditSiteLineDto target);

}
