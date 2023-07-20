package com.mycom.platform.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.platform.hr.entities.Employee;
import com.mycom.platform.hr.services.EmployeeService;;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET, path = "/companies/{companyId}/employees")
	public List<Employee> getAllEmployees(@PathVariable Long companyId) {
		return employeeService.findAllByCompanyId(companyId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/companies/{companyId}/employees/{employeeId}")
	public Employee getEmployee(@PathVariable Long companyId, @PathVariable Long employeeId) {
		return employeeService.findById(companyId, employeeId);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/companies/{companyId}/employees")
	public Employee createEmployee(@PathVariable Long companyId, @RequestBody Employee employee) {
		return employeeService.save(companyId, employee);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/companies/{companyId}/employees/{employeeId}")
	public Long deleteEmployee(@PathVariable Long companyId, @PathVariable Long employeeId) {
		return employeeService.deleteById(companyId, employeeId);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/companies/{companyId}/employees/{employeeId}")
	public Employee updateEmployee(@PathVariable Long companyId, @PathVariable Long employeeId,
			@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee, employeeId, companyId);
	}

}
