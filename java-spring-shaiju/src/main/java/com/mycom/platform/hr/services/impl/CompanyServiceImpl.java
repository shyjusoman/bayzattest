package com.mycom.platform.hr.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.platform.exceptions.EntityNotFoundException;
import com.mycom.platform.hr.entities.Company;
import com.mycom.platform.hr.repositories.CompanyRepository;
import com.mycom.platform.hr.services.CompanyService;
import com.mycom.platform.hr.type.ErrorCode;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{ 


	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public List<Company> findAll() {		
		return companyRepository.findAll();
	}

	@Override
	public Company findById(Long id) {
		Optional<Company> object = companyRepository.findById(id);
		if(object.isPresent()){
			return object.get();
		}else{
			throw new EntityNotFoundException(ErrorCode.COMPANY_NOT_FOUND_EXCEPTION,new Object[]{id});
		}
	}

	@Override
	public Long deleteById(Long id) {
		Company company=findById(id);
		companyRepository.delete(company);
		return id;
	}
	
	@Override
	public Company updateCompany(Company company,Long id) {
		Company companyInSystem=findById(id);
		BeanUtils.copyProperties(company, companyInSystem);
		return companyRepository.save(companyInSystem);
	}

	@Override
	public void validateCompany(Long id) {
		if(!companyRepository.existsById(id)){
			throw new EntityNotFoundException(ErrorCode.COMPANY_NOT_FOUND_EXCEPTION,new Object[]{id});
		}
		
	}

	@Override
	public List<Company> findByName(String name) {
		List<Company> lst=companyRepository.findByName(name);
		if(lst.isEmpty()){
			throw new EntityNotFoundException(ErrorCode.COMPANY_NOT_FOUND_EXCEPTION,new Object[]{name});
		}
		return lst;
	}
}
