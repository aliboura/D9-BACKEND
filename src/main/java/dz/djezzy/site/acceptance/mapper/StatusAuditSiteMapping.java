package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.StatusAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.StatusAuditSite;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StatusAuditSiteMapping extends GenericMapper<StatusAuditSite, StatusAuditSiteDto> {

    StatusAuditSiteMapping INSTANCE = Mappers.getMapper(StatusAuditSiteMapping.class);

    @Mappings({
            @Mapping(source = "status.id", target = "statusId"),
            @Mapping(source = "status.label", target = "statusLabel"),
            @Mapping(source = "auditSite.id", target = "auditSiteId")
    })
    @Override
    StatusAuditSiteDto toDto(StatusAuditSite source);

    @Mappings({
            @Mapping(target = "status.id", source = "statusId"),
            @Mapping(target = "status.label", source = "statusLabel"),
            @Mapping(target = "auditSite.id", source = "auditSiteId")
    })
    @Override
    StatusAuditSite toModel(StatusAuditSiteDto target);
}
