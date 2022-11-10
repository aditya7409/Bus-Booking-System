package com.cognizant.Audit.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.Audit.entity.Audit;

public interface AuditRepository extends CrudRepository<Audit, Integer> {

	
}
