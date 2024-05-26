package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Department;
import com.thymeleafspringbootapplication.model.DepartmentStatus;
import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.model.EmployeeStatus;
import com.thymeleafspringbootapplication.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImp implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveDepartments(Department department) {
        this.departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        Department department;
        if (optional.isPresent()) {
            department =  optional.get();
        } else {
            throw new RuntimeException(" Department not found for id :: " + id);
        }
        return department;
    }

    @Override
    public void deleteDepartmentById(long id) {
        this.departmentRepository.deleteById(id);
    }

    @Override
    public Map<String, Long> getDepartmentStatusCounts() {
        Long activeCount = departmentRepository.countByDepartmentStatus(DepartmentStatus.ACTIVE);
        Long inactiveCount = departmentRepository.countByDepartmentStatus(DepartmentStatus.INACTIVE);
        Map<String, Long> statusCounts = new HashMap<>();
        statusCounts.put("active", activeCount);
        statusCounts.put("inactive", inactiveCount);
        return statusCounts;
    }
}
