package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Department;
import com.thymeleafspringbootapplication.model.DepartmentStatus;
import com.thymeleafspringbootapplication.model.EmployeeStatus;
import com.thymeleafspringbootapplication.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/employee-index")
	public String viewEmployeeIndex(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "/Employee/index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// Create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		// Fetch all departments from database
		List<Department> departments = departmentService.getAllDepartments();
		List<Department> activeDepartments = departments.stream()
				.filter(department -> department.getDepartmentStatus() == DepartmentStatus.ACTIVE)
				.collect(Collectors.toList());
		model.addAttribute("departments", activeDepartments);
		return "/Employee/new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		if (employee.getEmployeeStatus() != null) {
			employee.setEmployeeStatus(EmployeeStatus.valueOf(employee.getEmployeeStatus().toString()));
		}
		return "redirect:/employee-index";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		List<Department> departments = departmentService.getAllDepartments();
		List<Department> activeDepartments = departments.stream()
				.filter(department -> department.getDepartmentStatus() == DepartmentStatus.ACTIVE)
				.collect(Collectors.toList());

		model.addAttribute("employee", employee);
		model.addAttribute("departments", activeDepartments);
		return "/Employee/update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/employee-index";
	}

	@GetMapping("/employee-status-counts")
	public ResponseEntity<Map<String, Long>> getEmployeeStatusCounts() {
		Map<String, Long> statusCounts = employeeService.getEmployeeStatusCounts();
		return ResponseEntity.ok(statusCounts);
	}

}
