package com.android.databasebuilder;

/**
 * Created by dulybon1 on 7/7/15.
 * Manager class
 */
public class Manager extends Employee
{
    //variables
    private int departmentNumber_;

    //constructor
    Manager(int id,String fName,String lName, String email,int deptNumber)
    {
        super(id,fName,lName, email);
        departmentNumber_ = deptNumber;

    }

    public int getDepartmentNumber()
    {
        return departmentNumber_;
    }

}