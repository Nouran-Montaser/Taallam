package com.example.nouran.taallam.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class HomePosts implements Parcelable {
    private String TeacherPictureURL;

    private int SharesNumber;

    private int BookID;

    private boolean IsLiked;

    private String BookName;

    private int LikesNumber;

    private String Body;

    private int PostID;

    private int CommentsNumber;

    private String TeacherName;

    private String Datetime;

    public String getTeacherPictureURL ()
{
    return TeacherPictureURL;
}

    public void setTeacherPictureURL (String TeacherPictureURL)
    {
        this.TeacherPictureURL = TeacherPictureURL;
    }

    public int getSharesNumber ()
    {
        return SharesNumber;
    }

    public void setSharesNumber (int SharesNumber)
    {
        this.SharesNumber = SharesNumber;
    }

    public int getBookID ()
    {
        return BookID;
    }

    public void setBookID (int BookID)
    {
        this.BookID = BookID;
    }

    public boolean getIsLiked ()
    {
        return IsLiked;
    }

    public void setIsLiked (boolean IsLiked)
    {
        this.IsLiked = IsLiked;
    }

    public String getBookName ()
    {
        return BookName;
    }

    public void setBookName (String BookName)
    {
        this.BookName = BookName;
    }

    public int getLikesNumber ()
    {
        return LikesNumber;
    }

    public void setLikesNumber (int LikesNumber)
    {
        this.LikesNumber = LikesNumber;
    }

    public String getBody ()
    {
        return Body;
    }

    public void setBody (String Body)
    {
        this.Body = Body;
    }

    public int getPostID ()
    {
        return PostID;
    }

    public void setPostID (int PostID)
    {
        this.PostID = PostID;
    }

    public int getCommentsNumber ()
    {
        return CommentsNumber;
    }

    public void setCommentsNumber (int CommentsNumber)
    {
        this.CommentsNumber = CommentsNumber;
    }

    public String getTeacherName ()
    {
        return TeacherName;
    }

    public void setTeacherName (String TeacherName)
    {
        this.TeacherName = TeacherName;
    }

    public String getDatetime ()
    {
        return Datetime;
    }

    public void setDatetime (String Datetime)
    {
        this.Datetime = Datetime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TeacherPictureURL = "+TeacherPictureURL+", SharesNumber = "+SharesNumber+", BookID = "+BookID+", IsLiked = "+IsLiked+", BookName = "+BookName+", LikesNumber = "+LikesNumber+", Body = "+Body+", PostID = "+PostID+", CommentsNumber = "+CommentsNumber+", TeacherName = "+TeacherName+", Datetime = "+Datetime+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TeacherPictureURL);
        dest.writeInt(this.SharesNumber);
        dest.writeInt(this.BookID);
        dest.writeByte(this.IsLiked ? (byte) 1 : (byte) 0);
        dest.writeString(this.BookName);
        dest.writeInt(this.LikesNumber);
        dest.writeString(this.Body);
        dest.writeInt(this.PostID);
        dest.writeInt(this.CommentsNumber);
        dest.writeString(this.TeacherName);
        dest.writeString(this.Datetime);
    }

    public HomePosts() {
    }

    protected HomePosts(Parcel in) {
        this.TeacherPictureURL = in.readString();
        this.SharesNumber = in.readInt();
        this.BookID = in.readInt();
        this.IsLiked = in.readByte() != 0;
        this.BookName = in.readString();
        this.LikesNumber = in.readInt();
        this.Body = in.readString();
        this.PostID = in.readInt();
        this.CommentsNumber = in.readInt();
        this.TeacherName = in.readString();
        this.Datetime = in.readString();
    }

    public static final Parcelable.Creator<HomePosts> CREATOR = new Parcelable.Creator<HomePosts>() {
        @Override
        public HomePosts createFromParcel(Parcel source) {
            return new HomePosts(source);
        }

        @Override
        public HomePosts[] newArray(int size) {
            return new HomePosts[size];
        }
    };
}

