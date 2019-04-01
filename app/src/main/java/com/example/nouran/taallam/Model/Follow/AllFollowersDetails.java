package com.example.nouran.taallam.Model.Follow;

import android.os.Parcel;
import android.os.Parcelable;

public class AllFollowersDetails implements Parcelable {

    private String FollowerPictureURL;

    private String Percentage;

    private String UserID;

    private String CategoryName;

    private String UserType;

    private String Name;

    public String getFollowerPictureURL ()
    {
        return FollowerPictureURL;
    }

    public void setFollowerPictureURL (String FollowerPictureURL)
    {
        this.FollowerPictureURL = FollowerPictureURL;
    }

    public String getPercentage ()
    {
        return Percentage;
    }

    public void setPercentage (String Percentage)
    {
        this.Percentage = Percentage;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    public String getCategoryName ()
    {
        return CategoryName;
    }

    public void setCategoryName (String CategoryName)
    {
        this.CategoryName = CategoryName;
    }

    public String getUserType ()
    {
        return UserType;
    }

    public void setUserType (String UserType)
    {
        this.UserType = UserType;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [FollowerPictureURL = "+FollowerPictureURL+", Percentage = "+Percentage+", UserID = "+UserID+", CategoryName = "+CategoryName+", UserType = "+UserType+", Name = "+Name+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FollowerPictureURL);
        dest.writeString(this.Percentage);
        dest.writeString(this.UserID);
        dest.writeString(this.CategoryName);
        dest.writeString(this.UserType);
        dest.writeString(this.Name);
    }

    public AllFollowersDetails() {
    }

    protected AllFollowersDetails(Parcel in) {
        this.FollowerPictureURL = in.readString();
        this.Percentage = in.readString();
        this.UserID = in.readString();
        this.CategoryName = in.readString();
        this.UserType = in.readString();
        this.Name = in.readString();
    }

    public static final Parcelable.Creator<AllFollowersDetails> CREATOR = new Parcelable.Creator<AllFollowersDetails>() {
        @Override
        public AllFollowersDetails createFromParcel(Parcel source) {
            return new AllFollowersDetails(source);
        }

        @Override
        public AllFollowersDetails[] newArray(int size) {
            return new AllFollowersDetails[size];
        }
    };
}

