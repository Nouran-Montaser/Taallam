package com.example.nouran.taallam.Model;

import java.util.ArrayList;

public class UserBook
{
    public UserBook(String userID, ArrayList<Integer> booksIDs) {
        UserID = userID;
        BooksIDs = booksIDs;
    }

    private String UserID;

    private ArrayList<Integer> BooksIDs;

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    public ArrayList<Integer> getBooksIDs ()
    {
        return BooksIDs;
    }

    public void setBooksIDs (ArrayList<Integer> BooksIDs)
    {
        this.BooksIDs = BooksIDs;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UserID = "+UserID+", BooksIDs = "+BooksIDs+"]";
    }
}


