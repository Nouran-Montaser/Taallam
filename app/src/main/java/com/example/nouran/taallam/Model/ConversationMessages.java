package com.example.nouran.taallam.Model;

public class ConversationMessages
{
    private boolean IsMine;

    private String Body;

    private String DateTime;

    private String MessageID;

    public boolean getIsMine ()
    {
        return IsMine;
    }

    public void setIsMine (boolean IsMine)
    {
        this.IsMine = IsMine;
    }

    public String getBody ()
    {
        return Body;
    }

    public void setBody (String Body)
    {
        this.Body = Body;
    }

    public String getDateTime ()
    {
        return DateTime;
    }

    public void setDateTime (String DateTime)
    {
        this.DateTime = DateTime;
    }

    public String getMessageID ()
    {
        return MessageID;
    }

    public void setMessageID (String MessageID)
    {
        this.MessageID = MessageID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsMine = "+IsMine+", Body = "+Body+", DateTime = "+DateTime+", MessageID = "+MessageID+"]";
    }
}

