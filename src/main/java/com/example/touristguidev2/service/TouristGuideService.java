package com.example.touristguidev2.service;

import com.example.touristguidev2.model.TouristAttraction;
import com.example.touristguidev2.repository.TouristGuideRepositoryDB;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TouristGuideService {

    /*private TouristGuideRepository touristGuideRepository;

    public TouristGuideService(TouristGuideRepository touristGuideRepository){
        this.touristGuideRepository = touristGuideRepository;
    }
    */

    private TouristGuideRepositoryDB touristGuideRepositoryDB;

    public TouristGuideService(TouristGuideRepositoryDB touristGuideRepositoryDB ){
        this.touristGuideRepositoryDB = touristGuideRepositoryDB;
    }



    public TouristAttraction getTouristAttraction(String name){
        return touristGuideRepositoryDB.getTouristAttraction(name);
    }


    public void deleteTouristAttraction(String name){
        touristGuideRepositoryDB.deleteTouristAttraction(name);
    }


    public void updateTouristAttraction(TouristAttraction touristAttraction){
        touristGuideRepositoryDB.updateTouristAttraction(touristAttraction);
    }


    public void createTouristAttraction(TouristAttraction touristAttraction){
        touristGuideRepositoryDB.addTouristAttraction(touristAttraction);
    }

    public ArrayList<String> getTags(String name){
        return touristGuideRepositoryDB.getTags(name);
    }

    public List<TouristAttraction> getTouristAttractions(){
        return touristGuideRepositoryDB.getTouristAttractions();
    }

    public List<String> getTagsList(){
        return touristGuideRepositoryDB.getTagsList();
    }
}



