package com.acciona.twitterapi.entity;

import twitter4j.GeoLocation;

public class TwitterData {
    
    private String name;
    private String text;
    private GeoLocation location;
    
    public TwitterData(String name, String text, GeoLocation location) {
        this.name = name;
        this.text = text;
        this.location = location;
    }
    
    public String getName() {
        return name;
    }
    
    public String getText() {
        return text;
    }
    
    public GeoLocation getLocation() {
        return location;
    }
}
