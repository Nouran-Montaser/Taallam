package com.example.nouran.taallam.Model;

public class User
{
    private boolean IsSuccess;

    private SearchUsers[] SearchUsers;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public SearchUsers[] getSearchUsers ()
    {
        return SearchUsers;
    }

    public void setSearchUsers (SearchUsers[] SearchUsers)
    {
        this.SearchUsers = SearchUsers;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", SearchUsers = "+SearchUsers+", ErrorMessage = "+ErrorMessage+"]";
    }
}

