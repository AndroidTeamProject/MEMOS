package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * This is the base class for employees
 * The Manager and Technologist classes
 * will extend this class
 */

public class Employee
{
    //variables
    private String EmployeeID_;
    private String firstName_;
    private String lastName_;
    private String email_;
    private String password_ = "DEFAULT";

    //constructor
    Employee(String employeeID, String firstName, String lastName, String email)
    {
        this.EmployeeID_ = employeeID;
        this.firstName_ = firstName;
        this.lastName_ = lastName;
        this.email_ = email;
    }

    //Getters
    public String getEmployeeID()
    {
        return EmployeeID_;
    }

    public String getFirstName()
    {
        return firstName_;
    }

    public String getLastName()
    {
        return lastName_;
    }

    public void setPassword(String password)
    {
        password_ = password;
    }

    public String getEmail() {
        return email_;
    }

    public String getPassword() {
        return password_;
    }
}
