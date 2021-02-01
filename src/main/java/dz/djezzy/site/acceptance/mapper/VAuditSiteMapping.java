package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.VAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.VAuditSite;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VAuditSiteMapping extends GenericMapper<VAuditSite, VAuditSiteDto> {

    VAuditSiteMapping INSTANCE = Mappers.getMapper(VAuditSiteMapping.class);
}
