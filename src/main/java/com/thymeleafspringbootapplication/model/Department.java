package com.thymeleafspringbootapplication.model;


import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "dep_code")
    private String depCode;

    @Column(name = "department_status")
    private DepartmentStatus departmentStatus;

    public DepartmentStatus getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(DepartmentStatus departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

}
