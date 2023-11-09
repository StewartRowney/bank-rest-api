package com.example.demo.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Person {

    //VARIABLES
    private final UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private LocalDateTime dateOfBirth;

    //CONSTRUCTOR
    public Person(String firstName, String middleName, String lastName, String address, String phoneNumber, String emailAddress, LocalDateTime dateOfBirth) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
    }

    //GETTERS
    public UUID getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    //SETTERS
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


}
