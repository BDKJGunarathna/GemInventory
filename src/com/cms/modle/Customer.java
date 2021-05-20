package com.cms.modle;

import javafx.scene.control.Button;



public class Customer {
    private Integer custId;
    private String fristName;
    private String lastName;
    private String email;
    private String address;
    private String contactNo;
    private String registeredDate;
    private Button update;
    private Button delete;


    //Constructor
    public Customer(Integer custId, String fristName, String lastName, String email, String address, String contactNo, String registeredDate, Button button1, Button button2) {
        this.custId = custId;
        this.fristName = fristName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.contactNo = contactNo;
        this.registeredDate = registeredDate;

    }



    public Customer() {
    }



    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }



    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }



    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }



    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }



    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
