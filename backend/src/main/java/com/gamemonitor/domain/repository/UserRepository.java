package com.gamemonitor.domain.repository;

import com.gamemonitor.domain.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public Optional<UserEntity> findByEmail(String email);
}
