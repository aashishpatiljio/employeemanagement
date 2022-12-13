package com.dxc.rest.controller;

import java.util.List;

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

import com.dxc.exception.EmployeeNotFoundException;
import com.dxc.exception.NoEmployeeRecordFoundException;
import com.dxc.model.Employee;
import com.dxc.service.IEmployeeService;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeRestController {
	@Autowired
	private IEmployeeService service;
	
	//to save record
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(
			@RequestBody Employee employee
			) {
		ResponseEntity<String> resp = null;
		try {
			Integer id = service.saveEmployee(employee);
			String message = new StringBuffer().append("Employee with ")
					.append(id).append(" is saved successfully").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.CREATED);
		} catch (Exception e) {
			String message = "Unable to save Employee";
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		    e.printStackTrace();
		}
		return resp;
	}
	
	//2. Fetch all records
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() {
		ResponseEntity<?> resp = null;
		List<Employee> empList = null;
		
		try {
			empList = service.getAllEmployees();
			//resp = new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);	
			if(empList.size() > 0) {
				resp = new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);				
			} else {
				throw new NoEmployeeRecordFoundException("Probably there are zero records in database");
			}
		} catch (NoEmployeeRecordFoundException nerfe) {
			String message = "Unable to fetch all records probably no record found";
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			nerfe.printStackTrace();			
		}
		return resp;
	}
	
	//3. Fetch one row by id
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getEmployeeById(
			@PathVariable Integer id
			) {
		ResponseEntity<?> resp = null;
		Employee employee = null;
		
		try {
			employee = service.getOneEmployee(id);
			resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} catch (EmployeeNotFoundException enfe) {
			throw enfe;
		} catch (Exception e) {
			String message = "Unable to fetch employee record";
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return resp;
		
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteEmployeeById(
			@PathVariable Integer id
			) {
		ResponseEntity<String> resp = null;
		
		try {
			service.deleteEmployee(id);
			String message = new StringBuffer().append("Employee with id ")
					.append(id).append(" is deleted successfully").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (EmployeeNotFoundException enfe) {
			throw enfe;			
		} catch(Exception e) {
			String message = new StringBuffer()
					.append("Unable to delete the employee record with id: ")
					.append(id).toString();
			
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}	
		
		return resp;		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployeeById(
			@PathVariable Integer id,
			@RequestBody Employee employee
			) {
		
		ResponseEntity<String> resp = null;
		
		try {
			Employee dbEmployee = service.getOneEmployee(id);
			
			/*
			 * Below four if statements are responsible for copying non-null
			 * values from client sent Employee object in @param employee to 
			 * the our fetched one Employee object by id i.e. dbEmployee.
			 *  
			 */
			if(employee.getEmpName() != null)
				dbEmployee.setEmpName(employee.getEmpName());
			if(employee.getEmpDept() != null)
				dbEmployee.setEmpDept(employee.getEmpDept());
			if(employee.getEmpMobNum() != null)
				dbEmployee.setEmpMobNum(employee.getEmpMobNum());
			if(employee.getEmpLocation() != null)
				dbEmployee.setEmpLocation(employee.getEmpLocation());
			
			//call service layer method to update the record
			service.updateEmployee(dbEmployee);
			
			String message = new StringBuffer()
					.append("Employee with id ")
					.append(id)
					.append(" is updated").toString();
			
			resp = new ResponseEntity<String>(message, HttpStatus.OK);
			
		} catch(EmployeeNotFoundException enfe) {
			throw enfe;			
		} catch(Exception e) {
			String message = new StringBuffer()
					.append("Employee with id '")
					.append(id)
					.append("' not updated").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);			
			e.printStackTrace();
		}		
		
		return resp;
		
	}
	

}
