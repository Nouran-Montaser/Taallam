package com.example.nouran.taallam.Model;

public class MessagesContactList
{
    private String LastMessageDate;

    private String LastMessage;

    private String ConversationUserID;

    private String ConversationUserImageUrl;

    private String ConversationUserName;

    private boolean IsSeen;

    public String getLastMessageDate ()
    {
        return LastMessageDate;
    }

    public void setLastMessageDate (String LastMessageDate)
    {
        this.LastMessageDate = LastMessageDate;
    }

    public String getLastMessage ()
    {
        return LastMessage;
    }

    public void setLastMessage (String LastMessage)
    {
        this.LastMessage = LastMessage;
    }

    public String getConversationUserID ()
    {
        return ConversationUserID;
    }

    public void setConversationUserID (String ConversationUserID)
    {
        this.ConversationUserID = ConversationUserID;
    }

    public String getConversationUserImageUrl ()
{
    return ConversationUserImageUrl;
}

    public void setConversationUserImageUrl (String ConversationUserImageUrl)
    {
        this.ConversationUserImageUrl = ConversationUserImageUrl;
    }

    public String getConversationUserName ()
    {
        return ConversationUserName;
    }

    public void setConversationUserName (String ConversationUserName)
    {
        this.ConversationUserName = ConversationUserName;
    }

    public boolean getIsSeen ()
    {
        return IsSeen;
    }

    public void setIsSeen (boolean IsSeen)
    {
        this.IsSeen = IsSeen;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [LastMessageDate = "+LastMessageDate+", LastMessage = "+LastMessage+", ConversationUserID = "+ConversationUserID+", ConversationUserImageUrl = "+ConversationUserImageUrl+", ConversationUserName = "+ConversationUserName+", IsSeen = "+IsSeen+"]";
    }
}


