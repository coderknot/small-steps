package com.epicodus.smallsteps.models;

import com.epicodus.smallsteps.ui.SearchActivity;

public class Group {
    String name; //name
    String category; //category: name
    String city; //city
    String state; //state
    String country; //country
    String description; //description
    int memberCount; //members
    String groupUrl; //link
    String imageUrl;

    public Group(String name,
                 String category,
                 String city,
                 String state,
                 String country,
                 String description,
                 int memberCount,
                 String groupUrl,
                 String imageUrl) {
        this.name = name;
        this.category = category;
        this.city = city;
        this.state = state;
        this.country = country;
        this.description = description;
        this.memberCount = memberCount;
        this.groupUrl = groupUrl;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDescription() {
        return this.description;
    }

    public int getMemberCount() {
        return this.memberCount;
    }

    public String getGroupUrl() {
        return this.groupUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getShortName() {
        if(this.getName().length() > 30) {
            return this.getName().substring(0, 30).concat("...");
        } else {
            return this.getName();
        }
    }

    public String getShortDescription() {
        if(this.getDescription().length() > 120) {
            return this.description.substring(0, 120).concat("...");
        } else {
            return this.getDescription();
        }
    }
}
