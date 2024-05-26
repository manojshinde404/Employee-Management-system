package com.thymeleafspringbootapplication.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Department;
import com.thymeleafspringbootapplication.model.EmployeeStatus;
import com.thymeleafspringbootapplication.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		Department department = departmentRepository.findById(employee.getDepartment().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Department id Not found with id :" + employee.getDepartment().getId()));
		employee.setDepartment(department);
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee;
		if (optional.isPresent()) {
			employee =  optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public Map<String, Long> getEmployeeStatusCounts() {
		Long activeCount = employeeRepository.countByEmployeeStatus(EmployeeStatus.ACTIVE);
		Long inactiveCount = employeeRepository.countByEmployeeStatus(EmployeeStatus.INACTIVE);
		Map<String, Long> statusCounts = new HashMap<>();
		statusCounts.put("active", activeCount);
		statusCounts.put("inactive", inactiveCount);
		return statusCounts;
	}

}
