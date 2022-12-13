package com.dxc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
