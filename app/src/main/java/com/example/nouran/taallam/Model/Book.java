package com.example.nouran.taallam.Model;

public class Book
{
    private boolean IsSuccess;

    private BooksList[] BooksList;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public BooksList[] getBooksList ()
    {
        return BooksList;
    }

    public void setBooksList (BooksList[] BooksList)
    {
        this.BooksList = BooksList;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", BooksList = "+BooksList+", ErrorMessage = "+ErrorMessage+"]";
    }
}

