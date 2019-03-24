package com.example.android_ma1.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {

    private int ID;
    private String name;
    private int teacher_id;
    private Teacher teacher;

    public Course(int ID, String name, int teacher_id) {
        this.ID = ID;
        this.name = name;
        this.teacher_id = teacher_id;
    }

    public Course(int ID, String name, Teacher teacher) {
        this.ID = ID;
        this.name = name;
        this.teacher = teacher;
        this.teacher_id = teacher.getId();
    }


    protected Course(Parcel in) {
        ID = in.readInt();
        name = in.readString();
        teacher_id = in.readInt();
        teacher = in.readParcelable(Teacher.class.getClassLoader());
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return name + " - "+teacher.getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(name);
        dest.writeInt(teacher_id);
        dest.writeParcelable(teacher, flags);
    }
}
