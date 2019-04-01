package com.example.nouran.taallam.Model;

public class BookData
{
    private BookDetails BookDetails;

    private boolean IsSuccess;

    private String ErrorMessage;

    public BookDetails getBookDetails ()
    {
        return BookDetails;
    }

    public void setBookDetails (BookDetails BookDetails)
    {
        this.BookDetails = BookDetails;
    }

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
        return "ClassPojo [BookDetails = "+BookDetails+", IsSuccess = "+IsSuccess+", ErrorMessage = "+ErrorMessage+"]";
    }
}

