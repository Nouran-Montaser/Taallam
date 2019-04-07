package com.example.nouran.taallam.Model;

import android.support.annotation.NonNull;

public class BooksList implements Comparable {

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private String CourseName;

    private int LevelNumber;

    private int ParticipantsNumber;

    private int ID;

    private String TeacherName;

    private String Name;

    private String sortBy;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public int getLevelNumber() {
        return LevelNumber;
    }

    public void setLevelNumber(int LevelNumber) {
        this.LevelNumber = LevelNumber;
    }

    public int getParticipantsNumber() {
        return ParticipantsNumber;
    }

    public void setParticipantsNumber(int ParticipantsNumber) {
        this.ParticipantsNumber = ParticipantsNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "ClassPojo [CourseName = " + CourseName + ", LevelNumber = " + LevelNumber + ", ParticipantsNumber = " + ParticipantsNumber + ", ID = " + ID + ", TeacherName = " + TeacherName + ", Name = " + Name + "]";
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if (sortBy.equals("Levels")) {
            int compareage = ((BooksList) o).LevelNumber;
            /* For Ascending order*/
            return this.LevelNumber - compareage;
        } else {
            int compareage = ((BooksList) o).ParticipantsNumber;
            /* For Ascending order*/
            return this.ParticipantsNumber - compareage;
        }
    }
}

