package com.bike.stores.dev.dto;

import org.springframework.web.bind.annotation.CrossOrigin;


public class CustomersDto {

    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String eMail;
    private String street;
    private String city;
    //private String state;
    private String zipCode;
    private String password;
    private String role;

    //Cons
    public CustomersDto(int customerId, String firstName, String lastName, String phone, String eMail, String street, String city, String zipCode, String password,String role) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
        this.street = street;
        this.city = city;
        //this.state = state;
        this.zipCode = zipCode;
        this.password = password;
        this.role = role;
    }

    //getter and setters


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

/*    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }*/

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
