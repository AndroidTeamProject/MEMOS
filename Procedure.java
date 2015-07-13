package com.android.databasebuilder;

/**
 * Created by dulybon1 on 7/7/15.
 * Pocedure class
 */
public class Procedure
{
    //PRIMARY KEY
    private long procedureID_;

    private String procedureName_;

    //THIS IS THE FOREIGN KEY FROM INSTRUMENT
    private long instrumentID_;

    /**this instance variable should only be able to take the values
     * Daily
     * Shift "meaning three time in a 24hr-period"
     * Weekly
     * Monthly
     * As Needed
    */
    private String frequency_;

    //constructor
    Procedure(long procID, String name, long inst_id, String f)
    {
        this.procedureID_ = procID;
        this.procedureName_ = name;
        this.instrumentID_ = inst_id;
        this.frequency_ = f;
    }

    public long getProcedureID()
    {
        return procedureID_;
    }

    public long getInstrumentID()
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
