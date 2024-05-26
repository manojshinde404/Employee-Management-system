package com.thymeleafspringbootapplication.model;

public enum DepartmentStatus {
    ACTIVE(0),
    INACTIVE(1);

    private final int statusValue;

    DepartmentStatus(int statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatusValue() {
        return statusValue;
    }
}
