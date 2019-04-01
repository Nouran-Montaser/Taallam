package com.example.nouran.taallam.Model;

public class Course
{
    private boolean IsSuccess;

    private com.example.nouran.taallam.Model.CoursesList[] CoursesList;

    private String ErrorMessage;

    public boolean getIsSuccess ()
    {
        return IsSuccess;
    }

    public void setIsSuccess (boolean IsSuccess)
    {
        this.IsSuccess = IsSuccess;
    }

    public CoursesList[] getCoursesList ()
    {
        return CoursesList;
    }

    public void setCoursesList (CoursesList[] CoursesList)
    {
        this.CoursesList = CoursesList;
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
        return "ClassPojo [IsSuccess = "+IsSuccess+", CoursesList = "+CoursesList+", ErrorMessage = "+ErrorMessage+"]";
    }
}


