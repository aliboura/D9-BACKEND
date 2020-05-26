package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface VisitPlanningService extends GenericService<VisitPlanning, VisitPlanningDto, Long> {

    Page<VisitPlanningDto> findByCities(List<Integer> citiesIds, Pageable pageable);

    Page<VisitPlanningDto> findByCode(String code, List<Integer> citiesIds, Pageable pageable);

    Page<VisitPlanningDto> findFirstVisitPlannings(String engineerSiteV1, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable);

    Page<VisitPlanningDto> findSecondVisitPlannings(String engineerSiteV2, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable);
}
