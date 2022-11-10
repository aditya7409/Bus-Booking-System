package com.userservice.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.userservice.data.UserEntity;

import feign.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	
	@Transactional
	@Modifying
	@Query("update UserEntity u set u.jwt = :token Where u.userId = :userId")
	void updateJwtByUserId(@Param("token") String token,@Param("userId") String userId);
}
