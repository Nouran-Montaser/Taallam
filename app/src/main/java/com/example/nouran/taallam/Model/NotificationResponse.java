package com.example.nouran.taallam.Model;

public class NotificationResponse {
    private boolean IsSuccess;

    private int NotificationNumber;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public int getNotificationNumber ()
    {
        return NotificationNumber;
    }

    public void setNotificationNumber (int NotificationNumber)
    {
        this.NotificationNumber = NotificationNumber;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", NotificationNumber = "+NotificationNumber+", ErrorMessage = "+ErrorMessage+"]";
    }
}

