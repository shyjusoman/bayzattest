package com.mycom.platform.hr;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.context.WebApplicationContext;

import com.mycom.platform.hr.entities.*;
import com.mycom.platform.hr.type.RelationShipType;
import com.mycom.platform.utils.JsonUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyComHrRestAPITest {

	private MockMvc mockMvc;
	
	private Company company;
	
	private Employee employee;
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
	
	private List<Dependant> dependents = new ArrayList<>();
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Before
	public void prepare () throws ParseException{
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
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
				andExpect(status().isOk()).andExpect(content().contentType(contentType)).
				andExpect(jsonPath("$[0].name", is(this.company.getName()))).
				andReturn().getResponse().getContentAsString();
		//List<Object> existingCompanyLst= jsonUtil.convertToListOfObjects(companyJosn,Company.class);
		//Company existingCompany=(Company)existingCompanyLst.get(0);
		
    }
	
	@Test
	public void test0004_createCompanyEmployee() throws Exception{
		prepareTestEmployee();
		String employeeJson = jsonUtil.convertToJson(this.employee);
		Long companyId=getCompanyId();
		String response=this.mockMvc.perform(post("/companies/"+companyId+"/employees")
                .contentType(contentType)
				.content(employeeJson))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.name", is(this.employee.getName())))				
				.andReturn().getResponse().getContentAsString();
		this.employee=(Employee) jsonUtil.convertToObject(response, Employee.class);
	}
	@Test
	public void test0005_updateCompanyEmployee() throws Exception{
		prepareTestEmployee();
		this.employee.setSalary(40000);		
		Long companyId=getCompanyId();
		Long employeeId=getEmployeeId();
		this.employee.setId(employeeId);
		String employeeJson = jsonUtil.convertToJson(this.employee);
		String response=this.mockMvc.perform(put("/companies/"+companyId+"/employees/"+employeeId)
                .contentType(contentType)
				.content(employeeJson))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.name", is(this.employee.getName())))
				.andExpect(jsonPath("$.salary", is(40000)))
				.andReturn().getResponse().getContentAsString();
		this.employee=(Employee) jsonUtil.convertToObject(response, Employee.class);
	}
    @Test
	public void test0006_getCompanyEmployees() throws Exception {
    	Long companyId=getCompanyId();
        mockMvc.perform(get("/companies/" + companyId+ "/employees/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", anything()));
    }
    
    @Test
	public void test0007_createEmployeeDependents() throws Exception{
		prepareTestDependents();
		Long companyId=getCompanyId();
		Long employeeId=getEmployeeId();
		for(Dependant dependant:this.dependents){
			String dependantJson = jsonUtil.convertToJson(dependant);
			String response = this.mockMvc.perform(post("/companies/"+companyId+"/employees/"+employeeId+"/dependants")
					.contentType(contentType).content(dependantJson))
					.andExpect(status().isOk()).andExpect(content().contentType(contentType)).andReturn().getResponse()
					.getContentAsString();
			dependant = (Dependant) jsonUtil.convertToObject(response, Dependant.class);
		}
	}
     
    @Test
   	public void test0008_getEmployeeDependants() throws Exception {
    	Long companyId=getCompanyId();
		Long employeeId=getEmployeeId();
           mockMvc.perform(get("/companies/"+companyId+"/employees/"+employeeId+"/dependants"))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(contentType))
                   .andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].id", anything()));
       }
    @Test
   	public void test0009_deleteEmployeeDependant() throws Exception {
    	Long companyId=getCompanyId();
		Long employeeId=getEmployeeId();
		Long dependantId=getDependantId();
		mockMvc.perform(delete("/companies/"+companyId+"/employees/"+employeeId+"/dependants/"+dependantId))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(contentType))
                   .andExpect(jsonPath("$", is(dependantId.intValue())));
       }
    @Test
   	public void test0010_getEmployeeDependantsAfterDelete() throws Exception {
    	Long companyId=getCompanyId();
		Long employeeId=getEmployeeId();
           mockMvc.perform(get("/companies/"+companyId+"/employees/"+employeeId+"/dependants"))
                   .andExpect(status().isOk())
                   .andExpect(content().contentType(contentType))
                   .andExpect(jsonPath("$", hasSize(2)));
       }
    @Test
	public void test0011_deleteCompanyEmployee() throws Exception{
		Long companyId=getCompanyId();
		Long employeeId=getEmployeeId();
		this.mockMvc.perform(delete("/companies/"+companyId+"/employees/"+employeeId))
				.andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", is(employeeId.intValue())));
		}
    
    @Test
	public void test0012_getCompanyEmployeesAfterDelete() throws Exception {
    	Long companyId=getCompanyId();
        mockMvc.perform(get("/companies/" + companyId+ "/employees/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(0)));
    }
    @Test
	public void test0013_entityNotFoundException() throws Exception {			
		mockMvc.perform(get("/companies/" + new Long(12234)))
		.andExpect(content().contentType(contentType))
		.andExpect(status().isNotFound()).andExpect(jsonPath("$.message", is("Company ,12234 Not Found")));
    }
	
    private void prepareTestCompany(){
    	this.company=new Company("BayZat","UAE");
    }
    
    private void prepareTestEmployee() throws ParseException{
    	//prepareTestCompany();
    	this.employee=new Employee(this.company, "Shaiju", "8129998515", "Male", sdf.parse("04/03/1982"), 30000);
    }
    
    private void prepareTestDependents() throws ParseException{
    	//prepareTestCompany();
    	Dependant dependent1=new Dependant("Shinju", "9645171203", sdf.parse("04/09/1984"), RelationShipType.HUSBAND.getRelationIdentifer(), employee);
		Dependant dependent2=new Dependant("Albin S Shaiju", "9645171203", sdf.parse("28/07/2011"), RelationShipType.FATHER.getRelationIdentifer(), employee);
		Dependant dependent3=new Dependant("Nivin S Shaiju", "9645171203", sdf.parse("22/10/2013"), RelationShipType.FATHER.getRelationIdentifer(), employee);
    	this.dependents.add(dependent1);
    	this.dependents.add(dependent2);
    	this.dependents.add(dependent3);
    }
    
    public Long getCompanyId() throws Exception {
    	prepare();
		prepareTestCompany();
		String companyJosn=mockMvc.perform(get("/companies/name/" + this.company.getName())).
				andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		List<Object> existingCompanyLst= jsonUtil.convertToListOfObjects(companyJosn,Company.class);
		Company existingCompany=(Company)existingCompanyLst.get(0);
		Long companyId=existingCompany.getId();
		return companyId;
		
    }
    
    public Long getEmployeeId() throws Exception {
    	Long companyId=getCompanyId();
		String employeesJosn=mockMvc.perform(get("/companies/"+companyId+"/employees")).
				andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		List<Object> existingEmployeeLst= jsonUtil.convertToListOfObjects(employeesJosn,Employee.class);
		Employee existingEmployee=(Employee)existingEmployeeLst.get(0);
		Long employeeId=existingEmployee.getId();
		return employeeId;
		
    }
    
    public Long getDependantId() throws Exception {
    	Long companyId=getCompanyId();
		Long employeeId=getEmployeeId();
		String employeesJosn=mockMvc.perform(get("/companies/"+companyId+"/employees/"+employeeId+"/dependants")).
				andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		List<Object> existingEmployeeLst= jsonUtil.convertToListOfObjects(employeesJosn,Dependant.class);
		Dependant existingDependant=(Dependant)existingEmployeeLst.get(0);
		Long dependantId=existingDependant.getId();
		return dependantId;
		
    }
    
}
