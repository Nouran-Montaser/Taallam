package com.example.nouran.taallam.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchUsers implements Parcelable {
    private String UserName;

    private String UserID;

    private String UserPictureURL;

    private String UserType;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUserPictureURL() {
        return UserPictureURL;
    }

    public void setUserPictureURL(String UserPictureURL) {
        this.UserPictureURL = UserPictureURL;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    @Override
    public String toString() {
        return "ClassPojo [UserName = " + UserName + ", UserID = " + UserID + ", UserPictureURL = " + UserPictureURL + ", UserType = " + UserType + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UserName);
        dest.writeString(this.UserID);
        dest.writeString(this.UserPictureURL);
        dest.writeString(this.UserType);
    }

    public SearchUsers() {
    }

    protected SearchUsers(Parcel in) {
        this.UserName = in.readString();
        this.UserID = in.readString();
        this.UserPictureURL = in.readString();
        this.UserType = in.readString();
    }

    public static final Parcelable.Creator<SearchUsers> CREATOR = new Parcelable.Creator<SearchUsers>() {
        @Override
        public SearchUsers createFromParcel(Parcel source) {
            return new SearchUsers(source);
        }

        @Override
        public SearchUsers[] newArray(int size) {
            return new SearchUsers[size];
        }
    };
}


