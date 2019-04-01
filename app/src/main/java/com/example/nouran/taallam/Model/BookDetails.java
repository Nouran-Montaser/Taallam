package com.example.nouran.taallam.Model;

public class BookDetails
{
    private String CourseName;

    private String TeacherPicture;

    private int FollowersNumber;

    private String TeacherTitle;

    private boolean IsFollower;

    private String Name;

    private String About;

    private boolean IsSubscriber;

    private int LevelNumber;

    private int ParticipantsNumber;

    private String FromDate;

    private String ToDate;

    private int ID;

    private String TeacherName;

    private String Link;

    public String getCourseName ()
    {
        return CourseName;
    }

    public void setCourseName (String CourseName)
    {
        this.CourseName = CourseName;
    }

    public String getTeacherPicture ()
    {
        return TeacherPicture;
    }

    public void setTeacherPicture (String TeacherPicture)
    {
        this.TeacherPicture = TeacherPicture;
    }

    public int getFollowersNumber ()
    {
        return FollowersNumber;
    }

    public void setFollowersNumber (int FollowersNumber)
    {
        this.FollowersNumber = FollowersNumber;
    }

    public String getTeacherTitle ()
    {
        return TeacherTitle;
    }

    public void setTeacherTitle (String TeacherTitle)
    {
        this.TeacherTitle = TeacherTitle;
    }

    public boolean getIsFollower ()
    {
        return IsFollower;
    }

    public void setIsFollower (boolean IsFollower)
    {
        this.IsFollower = IsFollower;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getAbout ()
    {
        return About;
    }

    public void setAbout (String About)
    {
        this.About = About;
    }

    public boolean getIsSubscriber ()
    {
        return IsSubscriber;
    }

    public void setIsSubscriber (boolean IsSubscriber)
    {
        this.IsSubscriber = IsSubscriber;
    }

    public int getLevelNumber ()
    {
        return LevelNumber;
    }

    public void setLevelNumber (int LevelNumber)
    {
        this.LevelNumber = LevelNumber;
    }

    public int getParticipantsNumber ()
    {
        return ParticipantsNumber;
    }

    public void setParticipantsNumber (int ParticipantsNumber)
    {
        this.ParticipantsNumber = ParticipantsNumber;
    }

    public String getFromDate ()
    {
        return FromDate;
    }

    public void setFromDate (String FromDate)
    {
        this.FromDate = FromDate;
    }

    public String getToDate ()
    {
        return ToDate;
    }

    public void setToDate (String ToDate)
    {
        this.ToDate = ToDate;
    }

    public int getID ()
    {
        return ID;
    }

    public void setID (int ID)
    {
        this.ID = ID;
    }

    public String getTeacherName ()
    {
        return TeacherName;
    }

    public void setTeacherName (String TeacherName)
    {
        this.TeacherName = TeacherName;
    }

    public String getLink ()
    {
        return Link;
    }

    public void setLink (String Link)
    {
        this.Link = Link;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CourseName = "+CourseName+", TeacherPicture = "+TeacherPicture+", FollowersNumber = "+FollowersNumber+", TeacherTitle = "+TeacherTitle+", IsFollower = "+IsFollower+", Name = "+Name+", About = "+About+", IsSubscriber = "+IsSubscriber+", LevelNumber = "+LevelNumber+", ParticipantsNumber = "+ParticipantsNumber+", FromDate = "+FromDate+", ToDate = "+ToDate+", ID = "+ID+", TeacherName = "+TeacherName+", Link = "+Link+"]";
    }
}

