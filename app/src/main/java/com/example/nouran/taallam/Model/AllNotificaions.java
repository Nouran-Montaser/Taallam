package com.example.nouran.taallam.Model;

public class AllNotificaions {
    private String Type;

    private String User;

    private String User1;

    private String UserID;

    private String NotificationBody;

    private String IsSeen;

    private String FromUserID;

    private String UserPictureURL;

    private String ID;

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getUser1() {
        return User1;
    }

    public void setUser1(String User1) {
        this.User1 = User1;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getNotificationBody() {
        return NotificationBody;
    }

    public void setNotificationBody(String NotificationBody) {
        this.NotificationBody = NotificationBody;
    }

    public String getIsSeen() {
        return IsSeen;
    }

    public void setIsSeen(String IsSeen) {
        this.IsSeen = IsSeen;
    }

    public String getFromUserID() {
        return FromUserID;
    }

    public void setFromUserID(String FromUserID) {
        this.FromUserID = FromUserID;
    }

    public String getUserPictureURL() {
        return UserPictureURL;
    }

    public void setUserPictureURL(String UserPictureURL) {
        this.UserPictureURL = UserPictureURL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ClassPojo [Type = " + Type + ", User = " + User + ", User1 = " + User1 + ", UserID = " + UserID + ", NotificationBody = " + NotificationBody + ", IsSeen = " + IsSeen + ", FromUserID = " + FromUserID + ", UserPictureURL = " + UserPictureURL + ", ID = " + ID + "]";
    }
}

