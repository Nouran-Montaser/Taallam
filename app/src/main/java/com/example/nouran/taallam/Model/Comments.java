package com.example.nouran.taallam.Model;

public class Comments
{
    private boolean IsSuccess;

    private PostComments[] PostComments;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public PostComments[] getPostComments ()
    {
        return PostComments;
    }

    public void setPostComments (PostComments[] PostComments)
    {
        this.PostComments = PostComments;
    }

    public String getErrorMessage ()
    {
        return ErrorMessage;
    }

    public void setErrorMessage (String ErrorMessage)
    {
        this.ErrorMessage = ErrorMessage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsSuccess = "+IsSuccess+", PostComments = "+PostComments+", ErrorMessage = "+ErrorMessage+"]";
    }
}


