package com.example.nouran.taallam.Model;

public class BookPosts {

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

    public String getTeacherPictureURL() {
        return TeacherPictureURL;
    }

    public void setTeacherPictureURL(String TeacherPictureURL) {
        this.TeacherPictureURL = TeacherPictureURL;
    }

    public int getSharesNumber() {
        return SharesNumber;
    }

    public void setSharesNumber(int SharesNumber) {
        this.SharesNumber = SharesNumber;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public boolean getIsLiked() {
        return IsLiked;
    }

    public void setIsLiked(boolean IsLiked) {
        this.IsLiked = IsLiked;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public int getLikesNumber() {
        return LikesNumber;
    }

    public void setLikesNumber(int LikesNumber) {
        this.LikesNumber = LikesNumber;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String Body) {
        this.Body = Body;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int PostID) {
        this.PostID = PostID;
    }

    public int getCommentsNumber() {
        return CommentsNumber;
    }

    public void setCommentsNumber(int CommentsNumber) {
        this.CommentsNumber = CommentsNumber;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String Datetime) {
        this.Datetime = Datetime;
    }

    @Override
    public String toString() {
        return "ClassPojo [TeacherPictureURL = " + TeacherPictureURL + ", SharesNumber = " + SharesNumber + ", BookID = " + BookID + ", IsLiked = " + IsLiked + ", BookName = " + BookName + ", LikesNumber = " + LikesNumber + ", Body = " + Body + ", PostID = " + PostID + ", CommentsNumber = " + CommentsNumber + ", TeacherName = " + TeacherName + ", Datetime = " + Datetime + "]";
    }
}

