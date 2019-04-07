package com.example.nouran.taallam.Model;

public class PostComment
{
    private Comment Comment;

    private String UserID;

    public PostComment(com.example.nouran.taallam.Model.Comment comment, String userID) {
        Comment = comment;
        UserID = userID;
    }

    public Comment getComment ()
    {
        return Comment;
    }

    public void setComment (Comment Comment)
    {
        this.Comment = Comment;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Comment = "+Comment+", UserID = "+UserID+"]";
    }
}

