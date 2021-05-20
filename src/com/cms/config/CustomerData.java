package com.cms.config;

import com.cms.modle.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public  class CustomerData {
    public static  Integer id;
    public static final String VALID = "VALID";


    //For the email validation
    public  static boolean emailValidate(String email) {
        //Regular expression
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        //To compile the pattern with regex
        Pattern pattern = Pattern.compile(regex);
        //To check if the email is matched with the pattern
        Matcher matcher = pattern.matcher(email);
        //To match the entire sequence against the pattern
        return  matcher.matches();
    }



    //For first name, last name, contact number validations
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            //To convert the string into double format
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    //For contact number validation
    public static boolean isValidLength(String strNum) {
        if (strNum == null) {
            return false;
        }
        if(strNum.length() == 10){
            return true;
        }
        return false;
    }



    public static String validation(Customer customer) {

        if(customer.getFristName().isEmpty()){
            return  "First name cannot be empty";
        }
        if(customer.getLastName().isEmpty()){
            return "Last name cannot be empty";
        }
        if(customer.getEmail().isEmpty()){
            return "Email cannot be empty";
        }
        if(customer.getAddress().isEmpty()){
            return "Address cannot be empty";
        }
        if(customer.getContactNo().isEmpty()){
            return "Contact number cannot be empty";
        }

        if(customer.getRegisteredDate().isEmpty()){
            return "Registration date cannot be empty";
        }

        if(CustomerData.isNumeric(customer.getFristName())){
            return  "Invalid first name";
        }

        if(CustomerData.isNumeric(customer.getLastName())){
            return "Invalid last name";
        }
        if(!CustomerData.emailValidate(customer.getEmail())){
            return "Invalid email address";
        }
        if(!CustomerData.isNumeric(customer.getContactNo())){
            return "Invalid contact number";
        }
        if(!CustomerData.isValidLength(customer.getContactNo())){
            return "Invalid contact number length";
        }


        return CustomerData.VALID;
    }
}
