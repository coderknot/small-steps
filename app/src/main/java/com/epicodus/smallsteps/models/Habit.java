package com.epicodus.smallsteps.models;

import org.parceler.Parcel;

@Parcel
public class Habit {
    String title;

    public Habit() { }

    public Habit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
