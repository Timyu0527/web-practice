package com.example.webapi.sight;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Sight")
public class Sight {
    @Id
    private int id;
    private String sightName;
    private String photoURL;
    private String address;
    private String zone;
    private String category;
    private String description;
    public Sight(int id, String sightName, String photoURL, String address,
                 String zone, String category, String description) {
        this.id = id;
        this.sightName = sightName;
        this.photoURL = photoURL;
        this.address = address;
        this.category = category;
        this.zone = zone;
        this.description = description;
    }
    public String getSightName(){
        return this.sightName;
    }
    public String getPhotoURL(){
        return this.photoURL;
    }
    public String getAddress(){
        return this.address;
    }
    public String getZone(){
        return this.zone;
    }
    public String getCategory(){
        return this.category;
    }
    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString() {
        return  "sightName: " + getSightName() + "\n" +
                "Zone: " + getZone() + "\n" +
                "Category: " + getCategory() + "\n" +
                "PhotoURL: " + getPhotoURL() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Address: " + getAddress();
    }
}

