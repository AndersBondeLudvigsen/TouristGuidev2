package com.example.touristguidev2.repository;


import com.example.touristguidev2.model.Tags;
import com.example.touristguidev2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristGuideRepository {
    private List<String> tagsList;
    private List<TouristAttraction> touristAttractions;
    public TouristGuideRepository() {
        tagsList = new ArrayList<>(List.of("Billige øl", "Gode pølser", "Sure fans", "Dårlig bane", "God bane", "Flot stadion"));
        touristAttractions = new ArrayList<>(List.of(
                new TouristAttraction("brøndby Stadion", "God stemning og gode pølser", new ArrayList<>(List.of("Gode pølser", "Sure fans"))),
                new TouristAttraction("nature Energy Park", "OBs Stadion. God pølsemix. Forvent sure fans", new ArrayList<>(List.of("God bane", "Flot stadion"))),
                new TouristAttraction("parken", "Største danske fodboldstadion, Tag kun derind når Danmarks landshold spiller", new ArrayList<>(List.of("Dårllig bane", "Sure fans"))),
                new TouristAttraction("right to dream park", "Ingen fans, men okay fodbold. DÅRLIG BANE", new ArrayList<>(List.of("Dårlig bane", "Flot stadion"))),
                new TouristAttraction("h", "h", new ArrayList<>(List.of("Billige øl"))
                )));
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public TouristAttraction getTouristAttraction(String name){
        for (TouristAttraction touristAttraction: touristAttractions) {
            if (touristAttraction.getName().equalsIgnoreCase(name)){
                return touristAttraction;
            }
        }
        return null;
    }


    public void updateTouristAttraction(TouristAttraction touristAttractionUpdated){
        int i = 0;
        while (i < touristAttractions.size()){
            if (touristAttractionUpdated.getName().equals(touristAttractions.get(i).getName())) {
                touristAttractions.set(i, touristAttractionUpdated);
            }
            i++;
        }
    }

    public void addTouristAttraction (TouristAttraction touristAttraction){
        touristAttractions.add(touristAttraction);
    }

    public void deleteTouristAttraction(String name) {
        int foundIndex = -1;

        for (int i = 0; i< touristAttractions.size(); i++) {
            if (name.equals(touristAttractions.get(i).getName())){
                foundIndex = i;
            }
        }
        if(foundIndex != -1) {
            touristAttractions.remove(foundIndex);
        }
    }

    public ArrayList<String> getTags(String name){
        int i = 0;
        while (i < touristAttractions.size()){
            if (name.toLowerCase().equals(touristAttractions.get(i).getName().toLowerCase())) {
                return touristAttractions.get(i).getTags();
            }
            i++;
        }
        return null;
    }

    public List<TouristAttraction> getTouristAttractions(){
        return touristAttractions;
    }


}

