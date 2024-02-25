package com.example.touristguidev2.model;

import java.util.ArrayList;

public class TouristAttraction {
    private String name;
    private String description;
    private ArrayList<String> tags;

    public TouristAttraction(String name, String description, ArrayList<String> tags){
        this.name = name;
        this.description = description;
        this.tags = tags;
    }
    public TouristAttraction(){
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTag(String tag){
        tags.add(tag);
    }

    public ArrayList<String> getTags(){
        return tags;
    }
//slet
    public TouristAttraction setTouristAttraction(TouristAttraction touristAttraction){
        setName(touristAttraction.getName());
        setDescription(touristAttraction.getDescription());
        return touristAttraction;

    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
