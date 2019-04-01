package com.example.nouran.taallam.Model;

public class CoursesList
{
    private int StudentsNumber;

    private int LevelsNumber;

    private String Description;

    private int TeachersNumber;

    private int QuestionsNumber;

    private int ID;

    private String Name;

    public int getStudentsNumber ()
    {
        return StudentsNumber;
    }

    public void setStudentsNumber (int StudentsNumber)
    {
        this.StudentsNumber = StudentsNumber;
    }

    public int getLevelsNumber ()
    {
        return LevelsNumber;
    }

    public void setLevelsNumber (int LevelsNumber)
    {
        this.LevelsNumber = LevelsNumber;
    }

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public int getTeachersNumber ()
    {
        return TeachersNumber;
    }

    public void setTeachersNumber (int TeachersNumber)
    {
        this.TeachersNumber = TeachersNumber;
    }

    public int getQuestionsNumber ()
    {
        return QuestionsNumber;
    }

    public void setQuestionsNumber (int QuestionsNumber)
    {
        this.QuestionsNumber = QuestionsNumber;
    }

    public int getID ()
    {
        return ID;
    }

    public void setID (int ID)
    {
        this.ID = ID;
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
        return "ClassPojo [StudentsNumber = "+StudentsNumber+", LevelsNumber = "+LevelsNumber+", Description = "+Description+", TeachersNumber = "+TeachersNumber+", QuestionsNumber = "+QuestionsNumber+", ID = "+ID+", Name = "+Name+"]";
    }
}

