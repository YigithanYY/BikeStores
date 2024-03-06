package com.bike.stores.dev.model;

import jakarta.persistence.*;



    @Entity
    @Table(name = "customers",schema = "sales")
    public class Customers  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "customer_id")
        private int customerId;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "phone")
        private String phone;

        @Column(name = "email",unique = true)
        private String eMail;

        @Column(name = "street")
        private String street;

        @Column(name = "city")
        private String city;

        @Column(name = "state")
        private String state;

        @Column(name = "zip_code")
        private String zipCode;

        @Column(name = "password")
        private String password;

        @Column(name = "role")
        private String role;






        // getters and setters

            public int getCustomerId() {
            return customerId;
        }

            public void setCustomerId (int customerId){
            this.customerId = customerId;
        }

            public String getFirstName () {
            return firstName;
        }

            public void setFirstName (String firstName){
            this.firstName = firstName;
        }

            public String getLastName () {
            return lastName;
        }

            public void setLastName (String lastName){
            this.lastName = lastName;
        }

            public String getPhone () {
            return phone;
        }

            public void setPhone (String phone){
            this.phone = phone;
        }

            public String getEmail () {
            return eMail;
        }

            public void setEmail (String eMail){
            this.eMail = eMail;
        }

            public String getStreet () {
            return street;
        }

            public void setStreet (String street){
            this.street = street;
        }

            public String getCity () {
            return city;
        }

            public void setCity (String city){
            this.city = city;
        }

            public String getState () {
            return state;
        }

            public void setState (String state){
            this.state = state;
        }

            public String getZipCode () {
            return zipCode;
        }

            public void setZipCode (String zipCode){
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

