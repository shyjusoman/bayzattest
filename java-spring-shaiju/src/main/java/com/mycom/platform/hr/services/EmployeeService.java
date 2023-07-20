package com.mycom.platform.hr.services;

import java.util.List;

import com.mycom.platform.hr.entities.Employee;

public interface EmployeeService {
	Employee save(Long companyId,Employee employee);

	List<Employee> findAllByCompanyId(Long companyId);

	Employee findById(Long companyId,Long employeeId);

	Long deleteById(Long companyId,Long employeeId);
	
	Employee  updateEmployee(Employee employee, Long employeeId,Long companyId);
	
	void validateEmployee (Long companyId,Long employeeId);
}
