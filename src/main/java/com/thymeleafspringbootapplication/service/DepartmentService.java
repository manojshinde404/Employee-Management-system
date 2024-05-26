package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Department;
import com.thymeleafspringbootapplication.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Department> getAllDepartments();
    void saveDepartments(Department department);
    Department getDepartmentById(long id);
    void deleteDepartmentById(long id);
    public Map<String, Long> getDepartmentStatusCounts();
}
