package dz.djezzy.site.acceptance.business.services.impl;

import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.Status;
import dz.djezzy.site.acceptance.business.repository.StatusRepository;
import dz.djezzy.site.acceptance.business.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusServiceIMPL extends GenericServiceImpl<StatusRepository, Status, StatusDto, Integer>
        implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public StatusDto findFirstStatus() {
        List<Status> list = statusRepository.findFirstStatus();
        return list != null && !list.isEmpty() ? asDto(list.get(0)) : null;
    }

    @Override
    public Optional<StatusDto> findByLabel(String label) {
        Optional<Status> opt = statusRepository.findByLabel(label);
        if (opt.isPresent()) {
            return Optional.of(asDto(opt.get()));
        }
        return Optional.empty();
    }
}
