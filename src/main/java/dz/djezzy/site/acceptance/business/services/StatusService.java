package dz.djezzy.site.acceptance.business.services;

import dz.djezzy.site.acceptance.business.data.dto.StatusDto;
import dz.djezzy.site.acceptance.business.data.entities.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService extends GenericService<Status, StatusDto, Integer> {

    StatusDto findFirstStatus();

    Optional<StatusDto> findByLabel(String label);
}
