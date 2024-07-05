package com.gamemonitor.domain.repository;

import com.gamemonitor.domain.model.TeamEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<TeamEntity, Long> {
}
