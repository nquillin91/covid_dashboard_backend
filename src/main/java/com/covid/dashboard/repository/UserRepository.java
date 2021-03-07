package com.covid.dashboard.repository.sql;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.covid.dashboard.model.sql.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);
}