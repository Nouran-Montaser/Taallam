package com.example.nouran.taallam.Model;

public class BooksList
{
    private String CourseName;

    private int LevelNumber;

    private int ParticipantsNumber;

    private int ID;

    private String TeacherName;

    private String Name;

    public String getCourseName ()
    {
        return CourseName;
    }

    public void setCourseName (String CourseName)
    {
        this.CourseName = CourseName;
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

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CourseName = "+CourseName+", LevelNumber = "+LevelNumber+", ParticipantsNumber = "+ParticipantsNumber+", ID = "+ID+", TeacherName = "+TeacherName+", Name = "+Name+"]";
    }
}

