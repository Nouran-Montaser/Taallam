package com.example.nouran.taallam.Model.Follow;

public class Followers
{
    private boolean IsSuccess;

    private AllFollowersDetails[] AllFollowersDetails;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public AllFollowersDetails[] getAllFollowersDetails ()
    {
        return AllFollowersDetails;
    }

    public void setAllFollowersDetails (AllFollowersDetails[] AllFollowersDetails)
    {
        this.AllFollowersDetails = AllFollowersDetails;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", AllFollowersDetails = "+AllFollowersDetails+", ErrorMessage = "+ErrorMessage+"]";
    }
}

