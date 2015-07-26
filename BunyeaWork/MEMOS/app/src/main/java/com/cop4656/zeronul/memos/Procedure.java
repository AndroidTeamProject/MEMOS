package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * Pocedure class
 */
public class Procedure
{
    //PRIMARY KEY
    private int procedureID_;

    private String procedureName_;

    //THIS IS THE FOREIGN KEY FROM INSTRUMENT
    private int instrumentID_;

    /**this instance variable should only be able to take the values
     * Daily
     * Shift "meaning three time in a 24hr-period"
     * Weekly
     * Monthly
     * As Needed
    */
    private String frequency_;

    //constructor
    Procedure(int procID, String name, int inst_id, String f)
    {
        this.procedureID_ = procID;
        this.procedureName_ = name;
        this.instrumentID_ = inst_id;
        this.frequency_ = f;
    }

    public int getProcedureID()
    {
        return procedureID_;
    }

    public int getInstrumentID()
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
