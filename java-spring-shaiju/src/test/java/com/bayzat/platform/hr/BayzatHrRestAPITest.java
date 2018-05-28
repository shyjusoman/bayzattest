package com.bayzat.platform.hr;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.hibernate.hql.internal.ast.tree.IsNullLogicOperatorNode;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import com.bayzat.platform.hr.entities.*;
import com.bayzat.platform.hr.repositories.*;
import com.bayzat.platform.hr.services.CompanyService;
import com.bayzat.platform.hr.services.DependantService;
import com.bayzat.platform.hr.services.EmployeeService;
import com.bayzat.platform.hr.type.RelationShipType;
import com.bayzat.platform.utils.JsonUtil;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BayzatHrRestAPITest {

	private MockMvc mockMvc;
	
	private Company company;
	
	private Employee employee;
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
	
	private List<Employee> employees = new ArrayList<>();
	
	private List<Dependant> dependents = new ArrayList<>();
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private CompanyService companyService;	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DependantService dependantService;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Before
	public void prepare () throws ParseException{
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		/*//this.company=new Company("BayZat","UAE");
		//companyService.save(company);
		
		//Employee employee=employeeService.save(this.company.getId(),new Employee(this.company, "Shaiju", "8129998515", "Male", sdf.parse("04/03/1982"), 30000));
		this.employee=new Employee(this.company, "Shaiju", "8129998515", "Male", sdf.parse("04/03/1982"), 30000);
		//Dependant dependent1=dependantService.save(this.company.getId(),employee.getId(),new Dependant("Shinju", "9645171203", new Date(), RelationShipType.HUSBAND.getRelationIdentifer(), employee));
		//Dependant dependent2=dependantService.save(this.company.getId(),employee.getId(),new Dependant("Albin S Shaiju", "9645171203", new Date(), RelationShipType.FATHER.getRelationIdentifer(), employee));
		//Dependant dependent3=dependantService.save(this.company.getId(),employee.getId(),new Dependant("Nivin S Shaiju", "9645171203", new Date(), RelationShipType.FATHER.getRelationIdentifer(), employee));
		Dependant dependent1=new Dependant("Shinju", "9645171203", new Date(), RelationShipType.HUSBAND.getRelationIdentifer(), employee);
		Dependant dependent2=new Dependant("Albin S Shaiju", "9645171203", new Date(), RelationShipType.FATHER.getRelationIdentifer(), employee);
		Dependant dependent3=new Dependant("Nivin S Shaiju", "9645171203", new Date(), RelationShipType.FATHER.getRelationIdentifer(), employee);
    	this.dependents.add(dependent1);
    	this.dependents.add(dependent2);
    	this.dependents.add(dependent3);
    	
    	employee.setDependats(this.dependents);
		this.employees.add(employee);
		company.setEmployees(this.employees);*/
	}
	
	@Test
	public void test0001_createCompany() throws Exception{
		prepareTestCompany();
		String companyJson = jsonUtil.convertToJson(this.company);
		String response=this.mockMvc.perform(post("/companies")
                .contentType(contentType)
				.content(companyJson))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType)).
				andReturn().getResponse().getContentAsString();
		company=(Company) jsonUtil.convertToObject(response, Company.class);
	}
	
	@Test
	public void test0002_getAllCompany() throws Exception {
		mockMvc.perform(get("/companies/")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
	               .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", anything()));
	    }
		
	@Test
	public void test0003_getCompanyByName() throws Exception {
		prepareTestCompany();
		String companyJosn=mockMvc.perform(get("/companies/name/" + this.company.getName())).
				andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		List<Object> existingCompanyLst= jsonUtil.convertToListOfObjects(companyJosn,Company.class);
		//Company existingCompany=(Company)existingCompanyLst.get(0);
		
    }
	
	@Test
	public void test0004_createCompanyEmployee() throws Exception{
		prepareTestEmployee();
		String employeeJson = jsonUtil.convertToJson(this.employee);
		Long companyId=getCompanyIdByName();
		String response=this.mockMvc.perform(post("/companies/"+companyId+"/employees")
                .contentType(contentType)
				.content(employeeJson))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType)).
				andReturn().getResponse().getContentAsString();
		this.employee=(Employee) jsonUtil.convertToObject(response, Employee.class);
	}
    @Test
	public void test0005_getCompanyEmployees() throws Exception {
    	Long companyId=getCompanyIdByName();
        mockMvc.perform(get("/companies/" + companyId+ "/employees/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", anything()));
    }
    
     
    @Test
	public void test0006_entityNotFoundException() throws Exception {			
		mockMvc.perform(get("/companies/" + new Long(12234)))
		.andExpect(content().contentType(contentType))
		.andExpect(status().isNotFound()).andExpect(jsonPath("$.message", is("Company ,12234 Not Found")));
    }
	
    private void prepareTestCompany(){
    	this.company=new Company("BayZat","UAE");
    }
    
    private void prepareTestEmployee() throws ParseException{
    	prepareTestCompany();
    	this.employee=new Employee(this.company, "Shaiju", "8129998515", "Male", sdf.parse("04/03/1982"), 30000);
    }
    
    public Long getCompanyIdByName() throws Exception {
    	prepare();
		prepareTestCompany();
		String companyJosn=mockMvc.perform(get("/companies/name/" + this.company.getName())).
				andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		List<Object> existingCompanyLst= jsonUtil.convertToListOfObjects(companyJosn,Company.class);
		Company existingCompany=(Company)existingCompanyLst.get(0);
		Long companyId=existingCompany.getId();
		return companyId;
		
    }
    
}
