package com.dxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.model.Employee;
import com.dxc.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService service;
	
	/**
	 * This method is invoked followed by the path "/register" on click
	 * of register page button or link.
	 * @param model helps to send the data to UI page. Here we are sending
	 * Form Backing Object.
	 * 
	 * @return "EmployeeRegister" page as a Logical View Name.
	 */
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		//sending form backing object new Employee()
		model.addAttribute("employee", new Employee());
		return "EmployeeRegister";
	}
	
	/**
	 * This method is invoked followed by the path "/save", on click of submit 
	 * or register button with POST type of http request to insert or save
	 * the entity in the database.
	 * @param employee reads the Employee object as ModelAttribute.
	 * @param model is used here to send the Data and Form Backing Object to
	 * UI.
	 * @return the logical view name as "EmployeeRegister".
	 */
	@PostMapping("/save")
	public String saveEmployee(
			@ModelAttribute Employee employee,
			Model model
			) {
		
		Integer id = service.saveEmployee(employee);
		String message = new StringBuffer()
				.append("Employee with id: ").append(id)
				.append(" is saved").toString();
			
		
		//sending data to UI page
		model.addAttribute("message", message);
		//form backing object
		model.addAttribute("employee", new Employee());
		return "EmployeeRegister";	
	}
	
	/**
	 * This method is invoked followed by the path "/all". It will
	 * fetch total number of records present in the DataBase and
	 * show it on the UI page.
	 * @param model send the data to UI
	 * @return the Logical View Name "EmployeeData".
	 */
	@GetMapping("/all")
	public String showAllEmployeesRecords(Model model) {
		model.addAttribute("list", service.getAllEmployees());
		return "EmployeeData";
	}
	
	/**
	 * This method is responsible for deletion of one record in a database
	 * by it's id followed by the path /employee/delete?id=15
	 * Ex path is :  http://localhost:9898/employee/delete?id=10
	 * @param id reads the id as a request param from the URL.
	 * @param model sends the data to UI
	 * @return and redirect to the path "/all"
	 */
	@GetMapping("/delete")
	public String deleteEmployee(
			@RequestParam Integer id,
			Model model
			) {
		service.deleteEmployee(id);
		String message = new StringBuffer()
				.append("Employee with id: ")
				.append(id).append(" is deleted successfully").toString();
		
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllEmployees());
		//return "EmployeeData";
		return "redirect:all";
	}
	
	/**
	 * This methods gives the edit page for a specific record in the database
	 * followed by the path "/employee/edit".
	 * @param id reads the id as a request param to fetch the specific record by it's id.
	 * @param model sends the data from controller to UI.
	 * @return the edit page logical view name i.e. "EmployeeEdit".
	 */
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Integer id,
			Model model
			) {		
		model.addAttribute("employee", service.getOneEmployee(id));
		return "EmployeeEdit";		
	}
	
	/**
	 * This method is responsible for updation of a specific record.
	 * It will be invoked followed by the path "/employee/update".
	 * 
	 * @param employee reads the Employee object as ModelAttribute and passed
	 * it to the service layer.
	 * @param model sends the data from controller to UI.
	 * @return "EmployeeData" as logical view name.
	 */
	@PostMapping("/update")
	public String updateEmployeeRecord(
			@ModelAttribute Employee employee,
			Model model
			) {
		//call to service layer
		service.updateEmployee(employee);
		String message = new StringBuffer()
				.append("Employee with id: ")
				.append(employee.getEmpId())
				.append(" is updated successfully").toString();
		
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllEmployees());
		return "EmployeeData";
		
	}
	

}
