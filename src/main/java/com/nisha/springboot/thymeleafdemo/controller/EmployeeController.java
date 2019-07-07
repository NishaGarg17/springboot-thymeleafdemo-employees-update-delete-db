package com.nisha.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nisha.springboot.thymeleafdemo.entity.Employee;
import com.nisha.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	 private EmployeeService employeeService;
	 
	 @Autowired
	 public EmployeeController(EmployeeService employeeService) {
		 this.employeeService = employeeService;
	 }
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model model) {
		// get employees form db
		List<Employee> employeeList = employeeService.findAll();
		model.addAttribute("employees",employeeList);
		return "employees/list-employees";
	}
	
	// add mapping to show form for add
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/employee-form";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int empId, Model model) {
		// get the employee from the Service
		Employee employee = employeeService.findById(empId);
		
		// set employee as model attribute to pre populate the form
		
		model.addAttribute("employee", employee);
		
		// send over to our form
		return "employees/employee-form";
	}
	
	// add post mapping to save an Employee
		@PostMapping("/save")
		public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
			// save the Employee
			employeeService.save(theEmployee);
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/employees/list";
		}
		
		// add get mappping for delete
		@GetMapping("/delete")
		public String deleteEmployee(@RequestParam("employeeId") int empId) {
			// delete the employee
			employeeService.deleteById(empId);
			
			// send over to our form
			return "redirect:/employees/list";
		}
}
