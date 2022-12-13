package com.dxc.service;

import java.util.List;

import com.dxc.model.Employee;

public interface IEmployeeService {
	
	Integer saveEmployee(Employee employee);
	
	List<Employee> getAllEmployees();
	
	void deleteEmployee(Integer id);
	
	Employee getOneEmployee(Integer id);
	
	void updateEmployee(Employee employee);
	

}
