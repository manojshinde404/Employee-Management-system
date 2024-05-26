package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Department;
import com.thymeleafspringbootapplication.model.DepartmentStatus;
import com.thymeleafspringbootapplication.model.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    long countByDepartmentStatus(DepartmentStatus status);
}
