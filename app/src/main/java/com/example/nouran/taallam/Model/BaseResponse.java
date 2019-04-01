package com.example.nouran.taallam.Model;

public class BaseResponse
{
    private boolean IsSuccess;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", ErrorMessage = "+ErrorMessage+"]";
    }
}
