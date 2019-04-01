package com.example.nouran.taallam.Model;

public class UserProfileDetails
{
    private int FollowersNumber;

    private boolean IsSuccess;

    private String UserName;

    private FourBooks[] FourBooks;

    private String UserID;

    private String about;

    private SixFollowers[] SixFollowers;

    private String UserPictureURL;

    private String IsFollowing;

    private String ErrorMessage;

    private String UserType;

    public int getFollowersNumber ()
    {
        return FollowersNumber;
    }

    public void setFollowersNumber (int FollowersNumber)
    {
        this.FollowersNumber = FollowersNumber;
    }

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public FourBooks[] getFourBooks ()
    {
        return FourBooks;
    }

    public void setFourBooks (FourBooks[] FourBooks)
    {
        this.FourBooks = FourBooks;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    public String getAbout ()
    {
        return about;
    }

    public void setAbout (String about)
    {
        this.about = about;
    }

    public SixFollowers[] getSixFollowers ()
    {
        return SixFollowers;
    }

    public void setSixFollowers (SixFollowers[] SixFollowers)
    {
        this.SixFollowers = SixFollowers;
    }

    public String getUserPictureURL ()
    {
        return UserPictureURL;
    }

    public void setUserPictureURL (String UserPictureURL)
    {
        this.UserPictureURL = UserPictureURL;
    }

    public String getIsFollowing ()
    {
        return IsFollowing;
    }

    public void setIsFollowing (String IsFollowing)
    {
        this.IsFollowing = IsFollowing;
    }

    public String getErrorMessage ()
    {
        return ErrorMessage;
    }

    public void setErrorMessage (String ErrorMessage)
    {
        this.ErrorMessage = ErrorMessage;
    }

    public String getUserType ()
    {
        return UserType;
    }

    public void setUserType (String UserType)
    {
        this.UserType = UserType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [FollowersNumber = "+FollowersNumber+", IsSuccess = "+IsSuccess+", UserName = "+UserName+", FourBooks = "+FourBooks+", UserID = "+UserID+", about = "+about+", SixFollowers = "+SixFollowers+", UserPictureURL = "+UserPictureURL+", IsFollowing = "+IsFollowing+", ErrorMessage = "+ErrorMessage+", UserType = "+UserType+"]";
    }
}


