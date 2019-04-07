package com.example.nouran.taallam.Model;

public class Login
{
    private boolean IsSuccess;

    private String UserID;

    private boolean IsFirstTime;

    private String ErrorMessage;

    private String UserType;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    public boolean getIsFirstTime ()
    {
        return IsFirstTime;
    }

    public void setIsFirstTime (boolean IsFirstTime)
    {
        this.IsFirstTime = IsFirstTime;
    }

    public String getErrorMessage ()
    {
        return ErrorMessage;
    }

    public void setErrorMessage (String ErrorMessage)
    {
        this.ErrorMessage = ErrorMessage;
    }

    public String getUserType ()
    {
        return UserType;
    }

    public void setUserType (String UserType)
    {
        this.UserType = UserType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsSuccess = "+IsSuccess+", UserID = "+UserID+", IsFirstTime = "+IsFirstTime+", ErrorMessage = "+ErrorMessage+", UserType = "+UserType+"]";
    }
}

