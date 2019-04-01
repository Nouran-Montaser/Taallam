package com.example.nouran.taallam.Model;

public class ForumBookPost {
    private String CourseName;

    private String TeacherPictureURL;

    private boolean IsSuccess;

    private BookPosts[] BookPosts;

    private String BookName;

    private String ErrorMessage;

    private String TeacherName;

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getTeacherPictureURL() {
        return TeacherPictureURL;
    }

    public void setTeacherPictureURL(String TeacherPictureURL) {
        this.TeacherPictureURL = TeacherPictureURL;
    }

    public boolean getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public BookPosts[] getBookPosts() {
        return BookPosts;
    }

    public void setBookPosts(BookPosts[] BookPosts) {
        this.BookPosts = BookPosts;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    @Override
    public String toString() {
        return "ClassPojo [CourseName = " + CourseName + ", TeacherPictureURL = " + TeacherPictureURL + ", IsSuccess = " + IsSuccess + ", BookPosts = " + BookPosts + ", BookName = " + BookName + ", ErrorMessage = " + ErrorMessage + ", TeacherName = " + TeacherName + "]";
    }
}


