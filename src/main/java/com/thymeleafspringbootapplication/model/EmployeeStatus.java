package com.thymeleafspringbootapplication.model;

public enum EmployeeStatus {
    ACTIVE(0),
    INACTIVE(1);

    private final int statusValue;

    EmployeeStatus(int statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatusValue() {
        return statusValue;
    }
}
