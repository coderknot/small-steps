package com.epicodus.smallsteps.models;

import org.parceler.Parcel;

@Parcel
public class Habit {
    String title;
    String reason;
    String pushId;
    String index;

    public Habit() { }

    public Habit(String title) {
        this.title = title;
        this.reason = "";
        this.index = "not_specified";
    }

    public Habit(String title, String reason) {
        this(title);
        this.reason = reason;
    }

    public String getTitle() {
        return this.title;
    }

    public String getReason() {
        return this.reason;
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
