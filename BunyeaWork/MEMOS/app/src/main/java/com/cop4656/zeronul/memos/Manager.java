package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * Manager class
 */
public class Manager extends Employee
{
    //variables
    private int departmentNumber_;

    //constructor
    Manager(String id,String fName,String lName, String email,int deptNumber, String password)
    {
        super(id,fName,lName, email);
        departmentNumber_ = deptNumber;

    }

    public int getDepartmentNumber()
    {
        return departmentNumber_;
    }

}
