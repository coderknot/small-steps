package com.epicodus.smallsteps.models;

import org.parceler.Parcel;

@Parcel
public class Habit {
    String title;
    String pushId;

    public Habit() { }

    public Habit(String title) {
        this.title = title;
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
}
