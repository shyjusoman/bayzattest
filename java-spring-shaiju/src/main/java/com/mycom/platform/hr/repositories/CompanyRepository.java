package com.mycom.platform.hr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycom.platform.hr.entities.Company;
import com.mycom.platform.hr.entities.Employee;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	List<Company> findByName(String name);
}
