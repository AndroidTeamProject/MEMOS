package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * This the log that we are keeping track of
 */
public class Log
{
    //PRIMARY KEY
    private int logID_;

    //this is the id of the instrument (FOREIGN KEY)
    private int instrumentID_;

    //procedure performed (FOREIGN KEY)
    private int procedureID_;

    //Who performed the procedure (FOREIGN KEY)
    private int techID_;

    //date procedure was performed
    private String date_;

    //time procedure was performed
    private String time_;

    //on what shift was it performed
    private String shift_;

    private String comment_;

    Log(int logID, int instrumentID, int procedureID,int techID,String date, String time, String shift, String comment)
    {
        this.logID_ = logID;
        this.instrumentID_ = instrumentID;
        this.procedureID_ = procedureID;
        this.techID_ = techID;
        this.date_ = date;
        this.shift_ = shift;
        this.time_ = time;
        this.comment_ = comment;
    }

    //public int getLogID() {return logID_;}

    public int getInstrumentID()
    {
        return instrumentID_;
    }

    public int getProcedureID()
    {
        return procedureID_;
    }

    public int getTechID()
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

    public String getComment()
    {
        return comment_;
    }


    public int getLogId() {
        return logID_;
    }
}
