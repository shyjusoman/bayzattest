package com.bayzat.platform.hr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bayzat.platform.hr.entities.Dependant;

@Repository
public interface DependantRepository extends JpaRepository<Dependant, Long> {
	List<Dependant> findByEmployeeId(Long employeeId);

}
