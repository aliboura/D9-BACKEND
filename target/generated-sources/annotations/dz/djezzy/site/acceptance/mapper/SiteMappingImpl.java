package dz.djezzy.site.acceptance.mapper;

import dz.djezzy.site.acceptance.business.data.dto.SiteDto;
import dz.djezzy.site.acceptance.business.data.entities.Site;
import dz.djezzy.site.acceptance.business.data.entities.TypeSite;
import dz.djezzy.site.acceptance.business.data.entities.Wilaya;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-05T09:45:07+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class SiteMappingImpl implements SiteMapping {

    @Override
    public List<SiteDto> toDto(Collection<Site> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<SiteDto> list = new ArrayList<SiteDto>( sourceList.size() );
        for ( Site site : sourceList ) {
            list.add( toDto( site ) );
        }

        return list;
    }

    @Override
    public List<Site> toModel(Collection<SiteDto> targetList) {
        if ( targetList == null ) {
            return null;
        }

        List<Site> list = new ArrayList<Site>( targetList.size() );
        for ( SiteDto siteDto : targetList ) {
            list.add( toModel( siteDto ) );
        }

        return list;
    }

    @Override
    public SiteDto toDto(Site source) {
        if ( source == null ) {
            return null;
        }

        SiteDto siteDto = new SiteDto();

        siteDto.setTypeSiteId( sourceTypeSiteId( source ) );
        siteDto.setTypeSiteLib( sourceTypeSiteTypeSiteLib( source ) );
        siteDto.setWilayaLabel( sourceWilayaLabel( source ) );
        siteDto.setWilayaId( sourceWilayaId( source ) );
        siteDto.setId( source.getId() );
        siteDto.setCodeSite( source.getCodeSite() );
        siteDto.setDateD1( source.getDateD1() );
        siteDto.setNomSite( source.getNomSite() );
        siteDto.setNumSite( source.getNumSite() );
        siteDto.setPrioriteRadio( source.getPrioriteRadio() );
        siteDto.setRegionId( source.getRegionId() );
        siteDto.setServiceDemandeur( source.getServiceDemandeur() );
        siteDto.setAudited( source.getAudited() );

        return siteDto;
    }

    @Override
    public Site toModel(SiteDto target) {
        if ( target == null ) {
            return null;
        }

        Site site = new Site();

        site.setWilaya( siteDtoToWilaya( target ) );
        site.setTypeSite( siteDtoToTypeSite( target ) );
        site.setId( target.getId() );
        site.setCodeSite( target.getCodeSite() );
        site.setDateD1( target.getDateD1() );
        site.setNomSite( target.getNomSite() );
        site.setNumSite( target.getNumSite() );
        site.setPrioriteRadio( target.getPrioriteRadio() );
        site.setRegionId( target.getRegionId() );
        site.setServiceDemandeur( target.getServiceDemandeur() );
        site.setAudited( target.getAudited() );

        return site;
    }

    private String sourceTypeSiteId(Site site) {
        if ( site == null ) {
            return null;
        }
        TypeSite typeSite = site.getTypeSite();
        if ( typeSite == null ) {
            return null;
        }
        String id = typeSite.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String sourceTypeSiteTypeSiteLib(Site site) {
        if ( site == null ) {
            return null;
        }
        TypeSite typeSite = site.getTypeSite();
        if ( typeSite == null ) {
            return null;
        }
        String typeSiteLib = typeSite.getTypeSiteLib();
        if ( typeSiteLib == null ) {
            return null;
        }
        return typeSiteLib;
    }

    private String sourceWilayaLabel(Site site) {
        if ( site == null ) {
            return null;
        }
        Wilaya wilaya = site.getWilaya();
        if ( wilaya == null ) {
            return null;
        }
        String label = wilaya.getLabel();
        if ( label == null ) {
            return null;
        }
        return label;
    }

    private int sourceWilayaId(Site site) {
        if ( site == null ) {
            return 0;
        }
        Wilaya wilaya = site.getWilaya();
        if ( wilaya == null ) {
            return 0;
        }
        int id = wilaya.getId();
        return id;
    }

    protected Wilaya siteDtoToWilaya(SiteDto siteDto) {
        if ( siteDto == null ) {
            return null;
        }

        Wilaya wilaya = new Wilaya();

        wilaya.setLabel( siteDto.getWilayaLabel() );
        wilaya.setId( siteDto.getWilayaId() );

        return wilaya;
    }

    protected TypeSite siteDtoToTypeSite(SiteDto siteDto) {
        if ( siteDto == null ) {
            return null;
        }

        TypeSite typeSite = new TypeSite();

        typeSite.setId( siteDto.getTypeSiteId() );
        typeSite.setTypeSiteLib( siteDto.getTypeSiteLib() );

        return typeSite;
    }
}
