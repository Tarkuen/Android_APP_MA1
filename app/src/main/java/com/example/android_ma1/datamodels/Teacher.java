package com.example.android_ma1.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Teacher implements Parcelable, Comparable {

    private int id;
    private String name;
    private String mail;
    private StringBuffer sb = new StringBuffer();

    private int[] rating = new int[6];

    public Teacher() {
    }

    public Teacher(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public Teacher(int id, String name, String mail, int[] rating) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.rating = rating;
    }


    protected Teacher(Parcel in) {
        id = in.readInt();
        name = in.readString();
        mail = in.readString();
        rating = in.createIntArray();
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int[] getRating() {
        return rating;
    }

    public void setRating(int[] rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        String results = "";
        String divider = "\t";
        String nl = "\n";

        results = sb.append(name).append('\n').append(Arrays.toString(rating)).toString();

        return results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(mail);
        dest.writeIntArray(rating);
    }

    @Override
    public int compareTo(Object o) {
        Teacher t = (Teacher) o;
        int counter = 0;
        int totalt= 0;
        for (int i : getRating()) {
            totalt+=i;
            counter++;
        }
        totalt = totalt/counter;

        int counter1 = 0;
        int total1 = 0;

        for (int i : ((Teacher) o).getRating()) {
            total1+=i;
            counter1++;
        }

        total1 = total1/counter1;

        return total1-totalt;
    }
}
