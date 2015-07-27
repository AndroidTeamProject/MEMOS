package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * Pocedure class
 */
public class Procedure
{
    //PRIMARY KEY
    private String procedureName_;

    //THIS IS THE FOREIGN KEY FROM INSTRUMENT
    private String instrumentID_;

    /**this instance variable should only be able to take the values
     * Daily
     * Shift "meaning three time in a 24hr-period"
     * Weekly
     * Monthly
     * As Needed
    */
    private String frequency_;

    //constructor
    Procedure(String name, String inst_id, String f)
    {
        this.procedureName_ = name;
        this.instrumentID_ = inst_id;
        this.frequency_ = f;
    }


    public String getInstrumentID()
    {
        return instrumentID_;
    }

    public String getProcedureName()
    {
        return procedureName_;
    }

    public String getFrequency()
    {
        return frequency_;
    }

}
