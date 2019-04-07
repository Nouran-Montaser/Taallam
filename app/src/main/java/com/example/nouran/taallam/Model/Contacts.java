package com.example.nouran.taallam.Model;

public class Contacts
{
    private boolean IsSuccess;

    private MessagesContactList[] MessagesContactList;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public MessagesContactList[] getMessagesContactList ()
    {
        return MessagesContactList;
    }

    public void setMessagesContactList (MessagesContactList[] MessagesContactList)
    {
        this.MessagesContactList = MessagesContactList;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", MessagesContactList = "+MessagesContactList+", ErrorMessage = "+ErrorMessage+"]";
    }
}


