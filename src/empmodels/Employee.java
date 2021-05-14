package empmodels;

import java.sql.Date;

public class Employee {

    //declare variables
    int empID, empPhone1, empPhone2;
    String empName, nic_number, empAddress, empType, empEmail, empSuspend;
    Date empDOB;
    double empSalary;

    //parameterized constructor
    public Employee(int empID, String empName, Date empDOB, String nic_number, String empAddress, int empPhone1, int empPhone2, String empType, String empEmail, double empSalary, String empSuspend) {
        this.empID = empID;
        this.empName = empName;
        this.empDOB = empDOB;
        this.nic_number = nic_number;
        this.empAddress = empAddress;
        this.empPhone1 = empPhone1;
        this.empPhone2 = empPhone2;
        this.empType = empType;
        this.empEmail = empEmail;
        this.empSalary = empSalary;
        this.empSuspend = empSuspend;
    }

    //getters and setters
    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getEmpPhone1() {
        return empPhone1;
    }

    public void setEmpPhone1(int empPhone1) {
        this.empPhone1 = empPhone1;
    }

    public int getEmpPhone2() {
        return empPhone2;
    }

    public void setEmpPhone2(int empPhone2) {
        this.empPhone2 = empPhone2;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getNic_number() {
        return nic_number;
    }

    public void setNic_number(String nic_number) {
        this.nic_number = nic_number;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpSuspend() {
        return empSuspend;
    }

    public void setEmpSuspend(String empSuspend) {
        this.empSuspend = empSuspend;
    }

    public Date getEmpDOB() {
        return empDOB;
    }

    public void setEmpDOB(Date empDOB) {
        this.empDOB = empDOB;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }
}
