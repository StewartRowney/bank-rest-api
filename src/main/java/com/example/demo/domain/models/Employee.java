package com.example.demo.domain.models;

import com.example.demo.domain.enums.Department;
import com.example.demo.domain.enums.EmployeeRole;
import com.example.demo.domain.enums.MedicalConditions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Employee extends Person{

    //VARIABLES
    private final LocalDateTime startDate;
    private BigDecimal salary;
    private EmployeeRole role;
    private Department department;
    private final List<MedicalConditions> medicalConditions;
    private int annualLeave;

    //CONSTRUCTOR
    public Employee(String firstName, String middleName, String lastName, String address, String phoneNumber, String emailAddress, LocalDateTime dateOfBirth, LocalDateTime startDate,
                    BigDecimal salary, EmployeeRole role, Department department, int annualLeave) {
        super(firstName, middleName, lastName, address, phoneNumber, emailAddress, dateOfBirth);
        this.startDate = startDate;
        this.salary = salary;
        this.role = role;
        this.department = department;
        this.medicalConditions = new ArrayList<>();
        this.annualLeave = annualLeave;
    }

    //GETTERS
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public EmployeeRole getRole() {
        return role;
    }
    public Department getDepartment() {
        return department;
    }
    public List<MedicalConditions> getMedicalConditions() {
        return medicalConditions;
    }
    public int getAnnualLeave() {
        return annualLeave;
    }

    //SETTERS
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    public void setRole(EmployeeRole role) {
        this.role = role;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public void setAnnualLeave(int annualLeave) {
        this.annualLeave = annualLeave;
    }
}
