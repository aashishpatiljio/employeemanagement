package com.dxc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "emp_tab")
public class Employee {
	@Id
	@GeneratedValue(generator = "employee")
	@Column(name = "emp_id_col")
	private Integer empId;
	@Column(name = "emp_name_col")
	private String empName;
	@Column(name = "emp_dept_col")
	private String empDept;
	@Column(name = "emp_mob_num_col")
	private String empMobNum;
	@Column(name = "emp_location_col")
	private String empLocation;

	public Employee() {
		
	}

	public Employee(Integer empId, String empName, String empDept, String empMobNum, String empLocation) {
		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.empMobNum = empMobNum;
		this.empLocation = empLocation;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getEmpMobNum() {
		return empMobNum;
	}

	public void setEmpMobNum(String empMobNum) {
		this.empMobNum = empMobNum;
	}

	public String getEmpLocation() {
		return empLocation;
	}

	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empDept=" + empDept + ", empMobNum=" + empMobNum
				+ ", empLocation=" + empLocation + "]";
	}

}
