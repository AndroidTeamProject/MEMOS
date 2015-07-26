package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * This the log that we are keeping track of
 */
public class Log
{
    //PRIMARY KEY
    private long logID_;

    //this is the id of the instrument (FOREIGN KEY)
    private long instrumentID_;

    //procedure performed (FOREIGN KEY)
    private long procedureID_;

    //Who performed the procedure (FOREIGN KEY)
    private long techID_;

    //date procedure was performed
    private String date_;

    //time procedure was performed
    private String time_;

    //on what shift was it performed
    private String shift_;

    Log(long logID,long instrumentID, long procedureID,long techID,String date, String time, String shift)
    {
        this.logID_ = logID;
        this.instrumentID_ = instrumentID;
        this.procedureID_ = procedureID;
        this.techID_ = techID;
        this.date_ = date;
        this.shift_ = shift;
        this.time_ = time;
    }

    public long getLogID()
    {
        return logID_;
    }

    public long getInstrumentID()
    {
        return instrumentID_;
    }

    public long getProcedureID()
    {
        return procedureID_;
    }

    public long getTechID()
    {
        return techID_;
    }

    public String getDate()
    {
        return date_;
    }

    public String getShift()
    {
        return shift_;
    }

    public String getTime()
    {
        return time_;
    }


}
