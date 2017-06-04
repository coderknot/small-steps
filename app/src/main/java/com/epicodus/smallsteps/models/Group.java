package com.epicodus.smallsteps.models;

import com.epicodus.smallsteps.ui.SearchActivity;

public class Group {
    String name; //name
    String link; //link
    String category; //category: name
    String city; //city
    String state; //state
    String country; //country
    String description; //description
    int memberCount; //members

    public Group(String name,
                 String link,
                 String category,
                 String city,
                 String state,
                 String country,
                 String description,
                 int memberCount) {
        this.name = name;
        this.link = link;
        this.category = category;
        this.city = city;
        this.state = state;
        this.country = country;
        this.description = description;
        this.memberCount = memberCount;
    }

    public String getName() {
        return this.name;
    }

    public String getLink() {
        return this.link;
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

    public String getShortDescription() {
        return this.description.substring(0, 120).concat("...");
    }
}
