package com.thymeleafspringbootapplication.service;

import java.util.List;
import java.util.Map;

import com.thymeleafspringbootapplication.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	public Map<String, Long> getEmployeeStatusCounts();
}
