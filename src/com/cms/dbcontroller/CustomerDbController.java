package com.cms.dbcontroller;

import com.cms.config.DBConnection;
import com.cms.contoller.CustomerDetailViewController;
import com.cms.modle.*;

import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class CustomerDbController extends CustomerDetailViewController {

    public String save(Customer customer) {
        Connection con = DBConnection.getConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO customer(firstname , lastname , email, address, contactno , registereddate) " +
                    " VALUES(? , ? ,? , ? ,? , ?) ");
            pstmt.setString(1, customer.getFristName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getContactNo());
            pstmt.setString(6, customer.getRegisteredDate());

            pstmt.executeUpdate();

            con.close();
        }
        catch (Exception e){
            //To return the detail message string of the throwable
            return e.getMessage();
        }

        return "Customer added to the database";
    }




    public List findAll(){
        Connection con = DBConnection.getConnection();
        ResultSet rs;

        List customerList = new ArrayList();

        try {
            PreparedStatement pstmt = con.prepareStatement(" select * from customer ");

            rs = pstmt.executeQuery();

            while (rs.next()){
                Customer customer = new Customer();

                //To get the details from the column
                customer.setCustId(rs.getInt("cusId"));
                customer.setFristName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setContactNo(rs.getString("contactNo"));
                customer.setRegisteredDate(rs.getString("registeredDate"));

                //Define a button
                Button update = new Button();
                //Set the text
                update.setText("Update");
                //Set the action
                update.setOnAction(super::update);
                update.setId(customer.getCustId().toString());
                customer.setUpdate(update);

                //Define a button
                Button delete = new Button();
                //Set the text
                delete.setText("Delete");
                //Set the action
                delete.setOnAction(super::delete);
                delete.setId(customer.getCustId().toString());
                customer.setDelete(delete);
                customerList.add(customer);
            }

            con.close();

        }catch (Exception e){
            return customerList;
        }

        return customerList;
    }



    public String remove(Integer id){
        Connection con = DBConnection.getConnection();
        ResultSet rs;

        List customerList = new ArrayList();

        try {
            PreparedStatement pstmt = con.prepareStatement(" delete  from customer where cusId = ? ");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            con.close();

            return "Customer removed successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            //To return the detail message string of the throwable
            return e.getMessage();
        }
    }

    public String update(Customer customer){
        Connection con = DBConnection.getConnection();
        ResultSet rs;

        List customerList = new ArrayList();

        try {
            PreparedStatement pstmt = con.prepareStatement(" update customer set firstName = ? , lastName = ? , email = ? , address = ? , contactNo = ? , registeredDate = ? where cusId = ? ");

            pstmt.setString(1, customer.getFristName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getContactNo());
            pstmt.setString(6, customer.getRegisteredDate());
            pstmt.setInt(7, customer.getCustId());

            pstmt.executeUpdate();
            con.close();

            return "Customer updated successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            //To return the detail message string of the throwable
            return e.getMessage();

        }
    }




    public Customer findById(Integer id){
        Connection con = DBConnection.getConnection();
        ResultSet rs;

        Customer customer = new Customer();

        try {
            PreparedStatement pstmt = con.prepareStatement(" select * from customer where cusId = ?");

            pstmt.setInt(1,id);

            rs = pstmt.executeQuery();

            while (rs.next()){
                customer.setCustId(rs.getInt("cusId"));
                customer.setFristName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setContactNo(rs.getString("contactNo"));
                customer.setRegisteredDate(rs.getString("registeredDate"));
            }

            con.close();
        }
        catch (Exception e){
            return customer;
        }

        return customer;
    }




    public Integer findByIdAndName(Integer id , String name){
        Connection con = DBConnection.getConnection();
        ResultSet rs;

        Customer customer = new Customer();

        try {
            PreparedStatement pstmt = con.prepareStatement(" select cusId from customer where cusId = ? or firstName = ? ");

            pstmt.setInt(1,id);
            pstmt.setString(2,name);

            rs = pstmt.executeQuery();

            while (rs.next()){
                id = rs.getInt("cusId");
            }

            con.close();

            return  id;
        }
        catch (Exception e){
            return -1;
        }
    }
}
