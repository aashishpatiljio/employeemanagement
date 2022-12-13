package com.dxc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.exception.EmployeeNotFoundException;
import com.dxc.model.Employee;
import com.dxc.repo.EmployeeRepository;
import com.dxc.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Integer saveEmployee(Employee employee) {
		return repo.save(employee).getEmpId();	
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return repo.findAll();
	}

	@Override
	public void deleteEmployee(Integer id) {
		Employee employee = repo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with " + id + " not exist"));
		
		repo.delete(employee);
		
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		Employee employee = repo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not exist"));
		return employee;
	}

	@Override
	public void updateEmployee(Employee employee) {
		repo.save(employee);
		
	}


}
