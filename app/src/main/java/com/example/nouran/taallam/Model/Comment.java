package com.example.nouran.taallam.Model;

public class Comment
{
    private String Comment;

    private int PostID;

    public Comment(String comment, int postID) {
        Comment = comment;
        PostID = postID;
    }

    public String getComment ()
    {
        return Comment;
    }

    public void setComment (String Comment)
    {
        this.Comment = Comment;
    }

    public int getPostID ()
    {
        return PostID;
    }

    public void setPostID (int PostID)
    {
        this.PostID = PostID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Comment = "+Comment+", PostID = "+PostID+"]";
    }
}


