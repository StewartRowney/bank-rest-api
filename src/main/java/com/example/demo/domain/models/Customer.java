package com.example.demo.domain.models;

import java.time.LocalDateTime;

public class Customer extends Person{

    //VARIABLES
    private final LocalDateTime dateAccountCreated;
    private boolean isEmployee;

    //CONSTRUCTOR
    public Customer(String firstName, String middleName, String lastName, String address, String phoneNumber, String emailAddress, LocalDateTime dateOfBirth, boolean isEmployee) {
        super(firstName, middleName, lastName, address, phoneNumber, emailAddress, dateOfBirth);
        dateAccountCreated = LocalDateTime.now();
        this.isEmployee = isEmployee;
    }

    //GETTERS
    public LocalDateTime getDateAccountCreated() {
        return dateAccountCreated;
    }
    public boolean isEmployee() {
        return isEmployee;
    }

    //SETTERS
    public void setIsEmployee(boolean employee) {
        isEmployee = employee;
    }


}
