package com.example.nouran.taallam.Model;

public class UnReadMessages
{
    private boolean IsSuccess;

    private int Count;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public int getCount ()
    {
        return Count;
    }

    public void setCount (int Count)
    {
        this.Count = Count;
    }

    public String getErrorMessage ()
    {
        return ErrorMessage;
    }

    public void setErrorMessage (String ErrorMessage)
    {
        this.ErrorMessage = ErrorMessage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsSuccess = "+IsSuccess+", Count = "+Count+", ErrorMessage = "+ErrorMessage+"]";
    }
}


