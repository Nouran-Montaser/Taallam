package com.example.nouran.taallam.Model;

public class FourBooks {
    private String BookID;

    private float Percentage;

    private String BookName;

    private int ParticipantsNumber;

    private String TeacherName;

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float Percentage) {
        this.Percentage = Percentage;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public int getParticipantsNumber() {
        return ParticipantsNumber;
    }

    public void setParticipantsNumber(int ParticipantsNumber) {
        this.ParticipantsNumber = ParticipantsNumber;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    @Override
    public String toString() {
        return "ClassPojo [BookID = " + BookID + ", Percentage = " + Percentage + ", BookName = " + BookName + ", ParticipantsNumber = " + ParticipantsNumber + ", TeacherName = " + TeacherName + "]";
    }
}

