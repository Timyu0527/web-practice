package com.example.WebAPI.sight;

public class Sight {
    private String sightName;
    private String photoURL;
    private String address;
    private String zone;
    private String category;
    private String description;
    public Sight(String _sightName, String _photoURL, String _address,
                 String _zone, String _category, String _description) {
        this.sightName = _sightName;
        this.photoURL = _photoURL;
        this.address = _address;
        this.category = _category;
        this.zone = _zone;
        this.description = _description;
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

