package com.example.android_ma1.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Rating implements Parcelable {

    private int id;
    private int[] value1;

    private int ifrom;
    private int ito;

    private Student from;
    private Teacher to;

    public Rating(int id, int[] value, Student from, Teacher to) {
        this.id = id;
        this.value1 = value;
        this.from = from;
        this.to = to;
    }

    public Rating(int id, int[] value1, int ifrom, int ito) {
        this.id = id;
        this.value1 = value1;
        this.ifrom = ifrom;
        this.ito = ito;
    }

    protected Rating(Parcel in) {
        id = in.readInt();
        value1 = in.createIntArray();
        ifrom = in.readInt();
        ito = in.readInt();
        to = in.readParcelable(Teacher.class.getClassLoader());
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    public int getIfrom() {
        return ifrom;
    }

    public void setIfrom(int ifrom) {
        this.ifrom = ifrom;
    }

    public int getIto() {
        return ito;
    }

    public void setIto(int ito) {
        this.ito = ito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getValue1() {
        return value1;
    }

    public void setValue1(int[] value1) {
        this.value1 = value1;
    }

    public Student getFrom() {
        return from;
    }

    public void setFrom(Student from) {
        this.from = from;
    }

    public Teacher getTo() {
        return to;
    }

    public void setTo(Teacher to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", value1=" + Arrays.toString(value1) +
                ", ifrom=" + ifrom +
                ", ito=" + ito +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeIntArray(value1);
        dest.writeInt(ifrom);
        dest.writeInt(ito);
        dest.writeParcelable(to, flags);
    }
}
