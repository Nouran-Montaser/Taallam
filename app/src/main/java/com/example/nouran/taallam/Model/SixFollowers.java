package com.example.nouran.taallam.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SixFollowers implements Parcelable {
    private String UserID;

    private String UserPictureURL;

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

    @Override
    public String toString()
    {
        return "ClassPojo [UserID = "+UserID+", UserPictureURL = "+UserPictureURL+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UserID);
        dest.writeString(this.UserPictureURL);
    }

    public SixFollowers() {
    }

    protected SixFollowers(Parcel in) {
        this.UserID = in.readString();
        this.UserPictureURL = in.readString();
    }

    public static final Parcelable.Creator<SixFollowers> CREATOR = new Parcelable.Creator<SixFollowers>() {
        @Override
        public SixFollowers createFromParcel(Parcel source) {
            return new SixFollowers(source);
        }

        @Override
        public SixFollowers[] newArray(int size) {
            return new SixFollowers[size];
        }
    };
}