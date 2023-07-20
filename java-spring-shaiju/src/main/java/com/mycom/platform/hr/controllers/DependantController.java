package com.mycom.platform.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.platform.hr.entities.Dependant;
import com.mycom.platform.hr.services.DependantService;

@RestController
public class DependantController {

	@Autowired
	DependantService dependantService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/companies/{companyId}/employees/{employeeId}/dependants")
	public List<Dependant> getAllDependants(@PathVariable Long companyId, @PathVariable Long employeeId) {
		return dependantService.findAllByEmployeeId(companyId, employeeId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/companies/{companyId}/employees/{employeeId}/dependants/{dependantId}")
	public Dependant getDependant(@PathVariable Long companyId, @PathVariable Long employeeId,
			@PathVariable Long dependantId) {
		return dependantService.findById(companyId, employeeId, dependantId);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/companies/{companyId}/employees/{employeeId}/dependants")
	public Dependant createDependant(@PathVariable Long companyId, @PathVariable Long employeeId,
			@RequestBody Dependant dependant) {
		return dependantService.save(companyId, employeeId, dependant);

	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/companies/{companyId}/employees/{employeeId}/dependants/{dependantId}")
	public Long deleteDependant(@PathVariable Long companyId, @PathVariable Long employeeId,
			@PathVariable Long dependantId) {
		return dependantService.deleteById(companyId, employeeId, dependantId);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/companies/{companyId}/employees/{employeeId}/dependants/{dependantId}")
	public Dependant updateDependant(@PathVariable Long companyId, @PathVariable Long employeeId,
			@PathVariable Long dependantId,
			@RequestBody Dependant dependant) {
		return dependantService.updateDependant(dependant, companyId, employeeId, dependantId);
	}

}
