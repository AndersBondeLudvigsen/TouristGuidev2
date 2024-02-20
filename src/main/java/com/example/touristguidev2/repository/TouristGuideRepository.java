package com.example.touristguidev2.repository;


import com.example.touristguidev2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristGuideRepository {
    private List<TouristAttraction> touristAttractions = new ArrayList<TouristAttraction>(List.of(
            new TouristAttraction("brøndby Stadion", "God stemning og gode pølser", new ArrayList<>(List.of("Gul", "Blå", "Hooligans", "Larmende"))),
            new TouristAttraction("nature Energy Park", "OBs Stadion. God pølsemix. Forvent sure fans", new ArrayList<>(List.of("Blå", "Hvid", "Thomas Helveg", "Løven Victor"))),
            new TouristAttraction("parken", "Største danske fodboldstadion, Tag kun derind når Danmarks landshold spiller", new ArrayList<>(List.of("Hvid", "Blå", "Stort", "København"))),
            new TouristAttraction("right to dream park", "Ingen fans, men okay fodbold. DÅRLIG BANE", new ArrayList<>(List.of("Rød", "Gul", "Farum"))),
            new TouristAttraction("h","h", new ArrayList<>(List.of("h", "h2")))
    ));



    public TouristAttraction getTouristAttraction(String name){
        for (TouristAttraction touristAttraction: touristAttractions) {
            if (touristAttraction.getName().equals(name)){
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
//ret
    public void deleteTouristAttraction(String name) {
        int foundIndex = -1;
        TouristAttraction returnTouristAttraction = new TouristAttraction("Not found","Not found");

        for (int i = 0; i< touristAttractions.size(); i++) {
            if (name.equals(touristAttractions.get(i).getName())){
                foundIndex = i;
            }
        }
        if(foundIndex != -1) {
            returnTouristAttraction = touristAttractions.get(foundIndex);
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

