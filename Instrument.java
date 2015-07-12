package com.android.databasebuilder;

/**
 * Created by dulybon1 on 7/7/15.
 * Instrument class
 */
public class Instrument
{
    private long instrumentID_;
    private String company_;
    private String model_;

    Instrument(long instID, String comp, String model)
    {
        this.instrumentID_ = instID;
        this.company_ = comp;
        this.model_ = model;
    }

    public long getInstrumentID()
    {
        return instrumentID_;
    }

    public String getCompany()
    {
        return company_;
    }

    public String getModel()
    {
        return model_;
    }

}
