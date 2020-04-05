package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.TypeAuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.TypeAuditSite;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeAuditSiteMapping extends GenericMapper<TypeAuditSite, TypeAuditSiteDto> {
}
