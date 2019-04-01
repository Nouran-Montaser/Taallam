package com.example.nouran.taallam.Model;

public class UserHomeDetails
{
    private boolean IsSuccess;

    private HomePosts[] HomePosts;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public HomePosts[] getHomePosts ()
    {
        return HomePosts;
    }

    public void setHomePosts (HomePosts[] HomePosts)
    {
        this.HomePosts = HomePosts;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", HomePosts = "+HomePosts+", ErrorMessage = "+ErrorMessage+"]";
    }
}


