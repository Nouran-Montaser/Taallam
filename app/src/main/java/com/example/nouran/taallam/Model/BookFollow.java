package com.example.nouran.taallam.Model;

public class BookFollow
{
    private boolean IsSuccess;

    private String BookID;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public String getBookID ()
    {
        return BookID;
    }

    public void setBookID (String BookID)
    {
        this.BookID = BookID;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", BookID = "+BookID+", ErrorMessage = "+ErrorMessage+"]";
    }
}

