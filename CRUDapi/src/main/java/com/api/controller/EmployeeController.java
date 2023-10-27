package com.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Employee;
import com.apiRepository.EmployeeRepository;

// Spring boot annotations are used in this layer



@RestController  // used to define the class as a controller in a Restful WebServices
@RequestMapping("/Sharma")  // used to map a web request to a method in controller layer/class
public class EmployeeController {
	
	
@Autowired // use to inject a bean in the class OR used for automatic dependency injection
EmployeeRepository employeeRepository;


@PostMapping("/Technology")  // @Postmapping is used to hand;e the post request in the Controller class
    public String createNewEmployee(@RequestBody Employee employee) {
	
	
//	here @RequestBody indicates that whatever data we are sending in the body it's mapping should be done properly with the object
//	@Requestbody is use to bind the request body to a method parameter in a controller class.
	
	employeeRepository.save(employee);
	return "Employee created in the Database";
	
    }


@GetMapping("/Technology") // used to handle the Get request in the controller class
    public ResponseEntity<List<Employee>> getAllEmployees(){
	List<Employee> empList = new ArrayList<>();
	employeeRepository.findAll().forEach(empList::add);
	return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	
    }
@GetMapping("/Technology/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//	@Pathvariable is used to extract a parameter from a URL in controller class
    Optional<Employee> employee = employeeRepository.findById(id);   
    if (employee.isPresent()) {
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
@PutMapping("/Technology/{empid}")  // used to handle PUT request in the controller class
    public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
			Optional<Employee> emp = employeeRepository.findById(empid);
			if(emp.isPresent()) {
				Employee existEmp = emp.get();
				existEmp.setEmp_age(employee.getEmp_age());
				existEmp.setEmp_city(employee.getEmp_city());
				existEmp.setEmp_name(employee.getEmp_name());
				existEmp.setEmp_salary(employee.getEmp_salary());
				employeeRepository.save(existEmp);
				return "Employee Details against Id" + " " + empid + " " + "updated";
				
			} else {
				return "Employee doesn't exist for empid" + " " + empid;
			}
        } 

@DeleteMapping("/Technology/{empid}")
public String deleteEmployeeByEmpId(@PathVariable Long empid) {
	employeeRepository.deleteById(empid);
	return "Employee Deleted Successfully";
}



@DeleteMapping("/Technology")
public String deleteAllEmployee() {
	employeeRepository.deleteAll();
	return "Employee Deleted Successfully...";

    }
}
