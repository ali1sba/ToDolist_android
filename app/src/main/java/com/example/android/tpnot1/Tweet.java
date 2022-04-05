package com.example.android.tpnot1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Tweet implements Parcelable {
    private String name;
    private String text;
    private Date date;
    private int imgURL;

    public Tweet(String name, String text, Date date, int imgURL) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.imgURL = imgURL;
    }

    protected Tweet(Parcel in) {
        name = in.readString();
        text = in.readString();
        imgURL = in.readInt();
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getImgURL() {
        return imgURL;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(text);
        parcel.writeInt(imgURL);
    }
}
