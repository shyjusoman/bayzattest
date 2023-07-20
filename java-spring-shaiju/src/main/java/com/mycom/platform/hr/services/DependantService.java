package com.mycom.platform.hr.services;

import java.util.List;

import com.mycom.platform.hr.entities.Dependant;

public interface DependantService {
	Dependant save(Long companyId,Long employeeId,Dependant dependant);

	List<Dependant>  findAllByEmployeeId(Long companyId,Long employeeId);

	Dependant findById(Long companyId,Long employeeId,Long dependantId);

	Long deleteById(Long companyId,Long employeeId,Long dependantId);
	
	Dependant updateDependant(Dependant dependant,Long companyId,Long employeeId,Long dependantId);
}
