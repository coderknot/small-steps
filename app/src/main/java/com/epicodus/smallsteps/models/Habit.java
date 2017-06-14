package com.epicodus.smallsteps.models;

import org.parceler.Parcel;

@Parcel
public class Habit {
    String title;
    String pushId;
    String index;

    public Habit() { }

    public Habit(String title) {
        this.title = title;
        this.index = "not_specified";
    }

    public String getTitle() {
        return this.title;
    }

    public String getPushId() {
        return this.pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
