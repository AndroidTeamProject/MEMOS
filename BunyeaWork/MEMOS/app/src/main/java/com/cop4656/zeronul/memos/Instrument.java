package com.cop4656.zeronul.memos;

/**
 * Created by dulybon1 on 7/7/15.
 * Instrument class
 */
public class Instrument
{
    private String instrumentID_;
    private String model_;

    Instrument(String instID, String model)
    {
        this.instrumentID_ = instID;
        this.model_ = model;
    }

    public String getInstrumentID()
    {
        return instrumentID_;
    }


    public String getModel()
    {
        return model_;
    }

}
