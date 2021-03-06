package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.AuditSiteDto;
import dz.djezzy.site.acceptance.business.data.entities.AuditSite;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.mapper.config.GenericMapper;
import dz.djezzy.site.acceptance.tools.SiteUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper(componentModel = "spring", uses = {
        CategoriesMapping.class,
        DecisionMapping.class,
        SiteMapping.class,
        StatusAuditSiteMapping.class,
        AuditSiteLineMapping.class})
public interface AuditSiteMapping extends GenericMapper<AuditSite, AuditSiteDto> {

    AuditSiteMapping INSTANCE = Mappers.getMapper(AuditSiteMapping.class);

    @Mappings({
            @Mapping(source = "typeAuditSite.id", target = "typeAuditSiteId"),
            @Mapping(source = "typeAuditSite.label", target = "typeAuditSiteLabel"),
            @Mapping(source = "site.id", target = "siteId"),
            @Mapping(source = "site.audited", target = "audited"),
            @Mapping(source = "site.codeSite", target = "siteCode"),
            @Mapping(source = "site.nomSite", target = "siteName"),
            @Mapping(source = "site.dateD1", target = "dateD1"),
            @Mapping(source = "site.typeSite.id", target = "typeSiteId"),
            @Mapping(source = "site.wilaya.label", target = "wilayaLabel"),
            @Mapping(source = "site.serviceDemandeur", target = "serviceDemandeur"),
            @Mapping(source = "currentSatus.id", target = "currentSatusId"),
            @Mapping(source = "currentSatus.label", target = "currentSatusLabel"),
            @Mapping(source = "currentSatus.description", target = "currentSatusDescription"),
            @Mapping(source = "currentSatus.styleCSS", target = "currentSatusStyleCSS"),
            @Mapping(source = "currentSatus.iconCSS", target = "currentSatusIconCSS"),
            @Mapping(source = "currentCategories.id", target = "currentCategoriesId"),
            @Mapping(source = "currentCategories.label", target = "currentCategoriesLabel"),
            @Mapping(source = "firstDecision.id", target = "firstDecisionId"),
            @Mapping(source = "firstDecision.label", target = "firstDecisionLabel"),
            @Mapping(source = "secondDecision.id", target = "secondDecisionId"),
            @Mapping(source = "secondDecision.label", target = "secondDecisionLabel"),
            @Mapping(source = "auditLineList", target = "auditSiteLineDtoList"),
            @Mapping(source = "statusAuditSitesList", target = "statusAuditSitesDtoList"),
            @Mapping(expression = "java(AuditSiteMapping.getUserV1(source.getSite()))", target = "siteUserV1"),
            @Mapping(expression = "java(AuditSiteMapping.getUserOMV1(source.getSite()))", target = "siteUserOMV1"),
            @Mapping(expression = "java(AuditSiteMapping.getUserDateV1(source.getSite()))", target = "siteUserDateV1"),
            @Mapping(expression = "java(AuditSiteMapping.getUserOMDateV1(source.getSite()))", target = "siteUserOMDateV1"),
            @Mapping(expression = "java(AuditSiteMapping.getUserV2(source.getSite()))", target = "siteUserV2"),
            @Mapping(expression = "java(AuditSiteMapping.getUserOMV2(source.getSite()))", target = "siteUserOMV2"),
            @Mapping(expression = "java(AuditSiteMapping.getUserDateV2(source.getSite()))", target = "siteUserDateV2"),
            @Mapping(expression = "java(AuditSiteMapping.getUserOMDateV2(source.getSite()))", target = "siteUserOMDateV2"),
    })
    @Override
    AuditSiteDto toDto(AuditSite source);

    @Mappings({
            @Mapping(target = "typeAuditSite.id", source = "typeAuditSiteId"),
            @Mapping(target = "typeAuditSite.label", source = "typeAuditSiteLabel"),
            @Mapping(target = "site.id", source = "siteId"),
            @Mapping(target = "site.audited", source = "audited"),
            @Mapping(target = "site.closed", source = "siteClosed"),
            @Mapping(target = "site.codeSite", source = "siteCode"),
            @Mapping(target = "site.nomSite", source = "siteName"),
            @Mapping(target = "site.dateD1", source = "dateD1"),
            @Mapping(target = "site.typeSite.id", source = "typeSiteId"),
            @Mapping(target = "site.wilaya.label", source = "wilayaLabel"),
            @Mapping(target = "site.serviceDemandeur", source = "serviceDemandeur"),
            @Mapping(target = "currentSatus.id", source = "currentSatusId"),
            @Mapping(target = "currentSatus.label", source = "currentSatusLabel"),
            @Mapping(target = "currentSatus.description", source = "currentSatusDescription"),
            @Mapping(target = "currentSatus.styleCSS", source = "currentSatusStyleCSS"),
            @Mapping(target = "currentSatus.iconCSS", source = "currentSatusIconCSS"),
            @Mapping(target = "currentCategories.id", source = "currentCategoriesId"),
            @Mapping(target = "currentCategories.label", source = "currentCategoriesLabel"),
            @Mapping(target = "firstDecision.id", source = "firstDecisionId"),
            @Mapping(target = "firstDecision.label", source = "firstDecisionLabel"),
            @Mapping(target = "secondDecision.id", source = "secondDecisionId"),
            @Mapping(target = "secondDecision.label", source = "secondDecisionLabel"),
            @Mapping(target = "auditLineList", source = "auditSiteLineDtoList"),
            @Mapping(target = "statusAuditSitesList", source = "statusAuditSitesDtoList")
    })
    @Override
    AuditSite toModel(AuditSiteDto target);

    @AfterMapping
    default AuditSite doAfterMapping(@MappingTarget AuditSite entity) {
        if (entity != null && entity.getSite().getId() == null) {
            entity.setSite(null);
        }
        if (entity != null && entity.getFirstDecision().getId() == null) {
            entity.setFirstDecision(null);
        }
        if (entity != null && entity.getSecondDecision().getId() == null) {
            entity.setSecondDecision(null);
        }
        if (entity != null && entity.getTypeAuditSite().getId() == null) {
            entity.setTypeAuditSite(null);
        }
        return entity;
    }

    static String getUserV1(Site source) {
        return SiteUtils.getUserV1(source);
    }

    static String getUserOMV1(Site source) {
        return SiteUtils.getUserOMV1(source);
    }


    static String getUserV2(Site source) {
        return SiteUtils.getUserV2(source);
    }

    static String getUserOMV2(Site source) {
        return SiteUtils.getUserOMV2(source);
    }

    static String getUserDateV1(Site source) {
        return SiteUtils.getUserDateV1String(source);
    }

    static String getUserDateV2(Site source) {
        return SiteUtils.getUserDateV2String(source);
    }

    static String getUserOMDateV1(Site source) {
        return SiteUtils.getUserOMDateV1String(source);
    }

    static String getUserOMDateV2(Site source) {
        return SiteUtils.getUserOMDateV2String(source);
    }

}
