package com.example.nouran.taallam.Model;

public class ConversationMessage {
    private boolean IsSuccess;

    private String ConversationUserID;

    private ConversationMessages[] ConversationMessages;

    private String ConversationUserImageUrl;

    private String ConversationUserName;

    private String ErrorMessage;

    public boolean getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public String getConversationUserID() {
        return ConversationUserID;
    }

    public void setConversationUserID(String ConversationUserID) {
        this.ConversationUserID = ConversationUserID;
    }

    public ConversationMessages[] getConversationMessages() {
        return ConversationMessages;
    }

    public void setConversationMessages(ConversationMessages[] ConversationMessages) {
        this.ConversationMessages = ConversationMessages;
    }

    public String getConversationUserImageUrl() {
        return ConversationUserImageUrl;
    }

    public void setConversationUserImageUrl(String ConversationUserImageUrl) {
        this.ConversationUserImageUrl = ConversationUserImageUrl;
    }

    public String getConversationUserName() {
        return ConversationUserName;
    }

    public void setConversationUserName(String ConversationUserName) {
        this.ConversationUserName = ConversationUserName;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    @Override
    public String toString() {
        return "ClassPojo [IsSuccess = " + IsSuccess + ", ConversationUserID = " + ConversationUserID + ", ConversationMessages = " + ConversationMessages + ", ConversationUserImageUrl = " + ConversationUserImageUrl + ", ConversationUserName = " + ConversationUserName + ", ErrorMessage = " + ErrorMessage + "]";
    }
}


