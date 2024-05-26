package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thymeleafspringbootapplication.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    long countByEmployeeStatus(EmployeeStatus status);
}
