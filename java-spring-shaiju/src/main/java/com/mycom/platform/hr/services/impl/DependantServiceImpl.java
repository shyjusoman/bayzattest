package com.mycom.platform.hr.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.platform.exceptions.EntityNotFoundException;
import com.mycom.platform.hr.entities.Dependant;
import com.mycom.platform.hr.entities.Employee;
import com.mycom.platform.hr.repositories.DependantRepository;
import com.mycom.platform.hr.services.CompanyService;
import com.mycom.platform.hr.services.DependantService;
import com.mycom.platform.hr.services.EmployeeService;
import com.mycom.platform.hr.type.ErrorCode;

@Service
@Transactional
public class DependantServiceImpl implements DependantService{

	@Autowired
	DependantRepository dependantRepository;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Override
	public Dependant save(Long companyId, Long employeeId, Dependant dependant) {
		companyService.validateCompany(companyId);
		Employee employee=employeeService.findById(companyId, employeeId);
		dependant.setEmployee(employee);
		return dependantRepository.save(dependant);
	}

	@Override
	public List<Dependant> findAllByEmployeeId(Long companyId,Long employeeId) {
		companyService.validateCompany(companyId);
		employeeService.validateEmployee(companyId,employeeId);
		return dependantRepository.findByEmployeeId(employeeId);
	}

	@Override
	public Dependant findById(Long companyId, Long employeeId, Long dependantId) {
		companyService.validateCompany(companyId);
		employeeService.validateEmployee(companyId,employeeId);
		Optional<Dependant> object=dependantRepository.findById(dependantId);
		if(object.isPresent()){
			return object.get();
		}else{
			throw new EntityNotFoundException(ErrorCode.DEPENDANT_NOT_FOUND_EXCEPTION,new Object[]{dependantId,employeeId,companyId});
		}
	}

	@Override
	public Long deleteById(Long companyId, Long employeeId, Long dependantId) {
		Dependant dependant=findById(companyId, employeeId, dependantId);
		dependantRepository.delete(dependant);
		return dependantId;
	}

	@Override
	public Dependant updateDependant(Dependant dependant, Long companyId, Long employeeId, Long dependantId) {
		Dependant dependantinSystem=findById(companyId, employeeId, dependantId);
		BeanUtils.copyProperties(dependant, dependantinSystem);
		return dependantRepository.save(dependantinSystem);
	}

}
