package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * This the log that we are keeping track of
 */
public class Log
{

    //this is the id of the instrument (FOREIGN KEY)
    private String instrumentID_;

    //procedure performed (FOREIGN KEY)
    private String procedureID_;

    //Who performed the procedure (FOREIGN KEY)
    private String techID_;

    //date procedure was performed
    private String date_;

    //time procedure was performed
    private String time_;

    //on what shift was it performed
    private String shift_;

    private String comment_;

    Log( String instrumentID, String procedureID,String techID,String date, String time, String shift, String comment)
    {

        this.instrumentID_ = instrumentID;
        this.procedureID_ = procedureID;
        this.techID_ = techID;
        this.date_ = date;
        this.shift_ = shift;
        this.time_ = time;
        this.comment_ = comment;
    }

    //public int getLogID() {return logID_;}

    public String getInstrumentID()
    {
        return instrumentID_;
    }

    public String getProcedureID()
    {
        return procedureID_;
    }

    public String getTechID()
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


}
