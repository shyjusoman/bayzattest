package com.mycom.platform.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.platform.hr.entities.Company;
import com.mycom.platform.hr.services.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(method=RequestMethod.POST,path="/companies")
	public Company createCompany(@RequestBody Company company){
		return companyService.save(company);		
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/companies")
	public List<Company> getAllCompanies() {
		return companyService.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/companies/{id}")
	public Company getCompanyById(@PathVariable Long id){
		return companyService.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/companies/name/{name}")
	public List<Company> getCompanyByName(@PathVariable String name){
		return companyService.findByName(name);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/companies/{id}")
	public Long deleteCompany(@PathVariable Long id) {
		return companyService.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/companies/{id}")
	public Company updateCompany(@RequestBody Company company, @PathVariable Long id) {
		return companyService.updateCompany(company, id);
	}
}
	
