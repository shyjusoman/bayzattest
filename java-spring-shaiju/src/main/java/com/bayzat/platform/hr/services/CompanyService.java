package com.bayzat.platform.hr.services;

import java.util.List;

import com.bayzat.platform.hr.entities.Company;

public interface CompanyService {

	Company save(Company company);

	List<Company> findAll();

	Company findById(Long id);

	Long deleteById(Long id);
	
	Company updateCompany(Company company,Long id);
	
	void validateCompany(Long id);
	
	List<Company> findByName(String name);

}
