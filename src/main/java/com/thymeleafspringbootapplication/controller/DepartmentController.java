package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Department;
import com.thymeleafspringbootapplication.model.DepartmentStatus;
import com.thymeleafspringbootapplication.model.Employee;
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

import java.util.Map;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department-index")
    public String viewDepartmentIndex(Model model) {
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "/Department/department_index";
    }

    @GetMapping("/showNewDepartmentForm")
    public String showNewDepartmentForm(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "/Department/new_department";
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartments(department);
        if (department.getDepartmentStatus() != null) {
            department.setDepartmentStatus(DepartmentStatus.valueOf(department.getDepartmentStatus().toString()));
        }
        return "redirect:/department-index";
    }

    @GetMapping("/showFormForDepUpdate/{id}")
    public String showFormForDepUpdate(@PathVariable(value="id") long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "/Department/update_department";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable (value = "id") long id) {
        this.departmentService.deleteDepartmentById(id);
        return "redirect:/department-index";
    }
    @GetMapping("/department-status-counts")
    public ResponseEntity<Map<String, Long>> getDepartmentStatusCounts() {
        Map<String, Long> statusCounts = departmentService.getDepartmentStatusCounts();
        return ResponseEntity.ok(statusCounts);
    }
}
