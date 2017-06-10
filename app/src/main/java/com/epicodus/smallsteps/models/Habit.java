package com.epicodus.smallsteps.models;

import org.parceler.Parcel;

@Parcel
public class Habit {
    String title;
    String uid;

    public Habit() { }

    public Habit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
