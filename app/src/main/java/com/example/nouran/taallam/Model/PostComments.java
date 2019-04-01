package com.example.nouran.taallam.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class PostComments implements Parcelable {
    private String UserName;

    private String UserID;

    private String UserPictureURL;

    private int ID;

    private String Body;

    private String DateTime;

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    public String getUserPictureURL ()
    {
        return UserPictureURL;
    }

    public void setUserPictureURL (String UserPictureURL)
    {
        this.UserPictureURL = UserPictureURL;
    }

    public int getID ()
    {
        return ID;
    }

    public void setID (int ID)
    {
        this.ID = ID;
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

    @Override
    public String toString()
    {
        return "ClassPojo [UserName = "+UserName+", UserID = "+UserID+", UserPictureURL = "+UserPictureURL+", ID = "+ID+", Body = "+Body+", DateTime = "+DateTime+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UserName);
        dest.writeInt(this.ID);
        dest.writeString(this.UserPictureURL);
        dest.writeString(this.UserID);
        dest.writeString(this.Body);
        dest.writeString(this.DateTime);
    }

    public PostComments() {
    }

    protected PostComments(Parcel in) {
        this.UserName = in.readString();
        this.ID = in.readInt();
        this.UserPictureURL = in.readString();
        this.UserID = in.readString();
        this.Body = in.readString();
        this.DateTime = in.readString();
    }

    public static final Parcelable.Creator<PostComments> CREATOR = new Parcelable.Creator<PostComments>() {
        @Override
        public PostComments createFromParcel(Parcel source) {
            return new PostComments(source);
        }

        @Override
        public PostComments[] newArray(int size) {
            return new PostComments[size];
        }
    };
}


