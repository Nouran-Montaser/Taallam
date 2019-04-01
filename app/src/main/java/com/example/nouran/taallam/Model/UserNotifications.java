package com.example.nouran.taallam.Model;

public class UserNotifications
{
    private boolean IsSuccess;

    private String ErrorMessage;

    private AllNotificaions[] AllNotificaions;

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

    public AllNotificaions[] getAllNotificaions ()
    {
        return AllNotificaions;
    }

    public void setAllNotificaions (AllNotificaions[] AllNotificaions)
    {
        this.AllNotificaions = AllNotificaions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsSuccess = "+IsSuccess+", ErrorMessage = "+ErrorMessage+", AllNotificaions = "+AllNotificaions+"]";
    }
}

