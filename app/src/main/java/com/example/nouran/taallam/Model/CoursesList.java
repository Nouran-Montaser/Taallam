package com.example.nouran.taallam.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CoursesList implements Parcelable {
    private boolean isSelected = false;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeInt(this.StudentsNumber);
        dest.writeInt(this.LevelsNumber);
        dest.writeString(this.Description);
        dest.writeInt(this.TeachersNumber);
        dest.writeInt(this.QuestionsNumber);
        dest.writeInt(this.ID);
        dest.writeString(this.Name);
    }

    public CoursesList() {
    }

    protected CoursesList(Parcel in) {
        this.isSelected = in.readByte() != 0;
        this.StudentsNumber = in.readInt();
        this.LevelsNumber = in.readInt();
        this.Description = in.readString();
        this.TeachersNumber = in.readInt();
        this.QuestionsNumber = in.readInt();
        this.ID = in.readInt();
        this.Name = in.readString();
    }

    public static final Parcelable.Creator<CoursesList> CREATOR = new Parcelable.Creator<CoursesList>() {
        @Override
        public CoursesList createFromParcel(Parcel source) {
            return new CoursesList(source);
        }

        @Override
        public CoursesList[] newArray(int size) {
            return new CoursesList[size];
        }
    };
}

