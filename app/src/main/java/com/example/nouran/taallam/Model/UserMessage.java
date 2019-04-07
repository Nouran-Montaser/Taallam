package com.example.nouran.taallam.Model;

public class UserMessage
{
    private Message Message;

    private String UserID;

    public UserMessage(com.example.nouran.taallam.Model.Message message, String userID) {
        Message = message;
        UserID = userID;
    }

    public Message getMessage ()
    {
        return Message;
    }

    public void setMessage (Message Message)
    {
        this.Message = Message;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Message = "+Message+", UserID = "+UserID+"]";
    }
}


