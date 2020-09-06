package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.VisitPlanningDto;
import dz.djezzy.site.acceptance.business.data.entities.VisitPlanning;
import dz.djezzy.site.acceptance.business.repository.VisitPlanningRepository;
import dz.djezzy.site.acceptance.business.services.VisitPlanningService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class VisitPlanningServiceIMPL extends GenericServiceImpl<VisitPlanningRepository, VisitPlanning, VisitPlanningDto, Long>
        implements VisitPlanningService {

    private final VisitPlanningRepository visitPlanningRepository;

    @Override
    public Page<VisitPlanningDto> findByCities(List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findByCities(citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Optional<VisitPlanningDto> findBySiteId(Long id) {
        Optional<VisitPlanning> opt = visitPlanningRepository.findBySiteId(id);
        return opt.isPresent() ? Optional.ofNullable(asDto(opt.get())) : Optional.empty();
    }

    @Override
    public Page<VisitPlanningDto> findByCode(String code, List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findByCode(code, citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findFirstVisitPlannings(String engineerSiteV1, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findFirstVisitPlannings(engineerSiteV1, fromDate, toDate, citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findSecondVisitPlannings(String engineerSiteV2, Date fromDate, Date toDate, List<Integer> citiesIds, Pageable pageable) {
        return visitPlanningRepository.findSecondVisitPlannings(engineerSiteV2, fromDate, toDate, citiesIds, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findEngineerPlannings(String username, Pageable pageable) {
        return visitPlanningRepository.findEngineerPlannings(username, pageable).map(data -> asDto(data));
    }

    @Override
    public Page<VisitPlanningDto> findOMPlannings(String username, Pageable pageable) {
        return visitPlanningRepository.findOMPlannings(username, pageable).map(data -> asDto(data));
    }

    @Override
    public Integer countEngineer(String username, Date date) {
        return visitPlanningRepository.countEngineer(username, date);
    }

    @Override
    public Integer countEngineerOM(String username, Date date) {
        return visitPlanningRepository.countEngineerOM(username, date);
    }
}
