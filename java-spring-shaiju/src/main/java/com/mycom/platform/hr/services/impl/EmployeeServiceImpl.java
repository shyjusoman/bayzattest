package com.mycom.platform.hr.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.platform.exceptions.EntityNotFoundException;
import com.mycom.platform.hr.entities.Company;
import com.mycom.platform.hr.entities.Employee;
import com.mycom.platform.hr.repositories.EmployeeRepository;
import com.mycom.platform.hr.services.CompanyService;
import com.mycom.platform.hr.services.EmployeeService;
import com.mycom.platform.hr.type.ErrorCode;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyService companyService;

	@Override
	public Employee save(Long companyId,Employee employee) {
		Company company=companyService.findById(companyId);
		employee.setCompany(company);
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAllByCompanyId(Long companyId) {
		companyService.validateCompany(companyId);
		return employeeRepository.findByCompanyId(companyId);
	}

	@Override
	public Employee findById(Long companyId,Long employeeId) {		
		Optional<Employee> object=employeeRepository.findById(employeeId);
		if(object.isPresent()){
			return object.get();
		}else{
			throw new EntityNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND_EXCEPTION,new Object[]{employeeId,companyId});
		}
	}

	@Override
	public Long deleteById(Long companyId,Long employeeId) {
		Employee employee=findById(companyId, employeeId);
		employeeRepository.delete(employee);
		return employeeId;
	}

	@Override
	public Employee updateEmployee(Employee employee, Long employeeId,Long companyId) {
		companyService.validateCompany(companyId);
		Employee employeeInSystem=findById(companyId, employeeId);
		Company company=employeeInSystem.getCompany();
		BeanUtils.copyProperties(employee, employeeInSystem);
		employeeInSystem.setCompany(company);
		return employeeRepository.save(employeeInSystem);
	}

	@Override
	public void validateEmployee(Long companyId,Long employeeId) {
		if(!employeeRepository.existsById(employeeId)){
			throw new EntityNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND_EXCEPTION,new Object[]{employeeId,companyId});
		}
		
	}
	
}
