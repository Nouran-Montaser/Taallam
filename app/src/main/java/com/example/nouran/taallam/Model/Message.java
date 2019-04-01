package com.example.nouran.taallam.Model;

public class Message {
    String ToUserID;
    String Body;

    public Message(String toUserID, String body) {
        ToUserID = toUserID;
        Body = body;
    }

    public String getToUserID() {
        return ToUserID;
    }

    public void setToUserID(String ToUserID) {
        this.ToUserID = ToUserID;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String Body) {
        this.Body = Body;
    }
}
