package com.android.databasebuilder;

/**
 * Created by dulybon1 on 7/7/15.
 * This is the base class for employees
 * The Manager and Technologist classes
 * will extend this class
 */

public class Employee
{
    //variables
    private int EmployeeID_;
    private String firstName_;
    private String lastName_;
    private String email_;
    private String password_ = "DEFAULT";

    //constructor
    Employee(int employeeID, String firstName, String lastName, String email)
    {
        this.EmployeeID_ = employeeID;
        this.firstName_ = firstName;
        this.lastName_ = lastName;
        this.email_ = email;
    }

    //Getters
    public int getEmployeeID()
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
}
