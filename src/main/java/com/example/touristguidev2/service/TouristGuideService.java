package com.example.touristguidev2.service;

import com.example.touristguidev2.model.TouristAttraction;
import com.example.touristguidev2.repository.TouristGuideRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TouristGuideService {
    private TouristGuideRepository touristGuideRepository;

    public TouristGuideService(TouristGuideRepository touristGuideRepository){
        this.touristGuideRepository = touristGuideRepository;
    }


    public TouristAttraction getTouristAttraction(String name){
        return touristGuideRepository.getTouristAttraction(name);
    }


    public void deleteTouristAttraction(String name){
        touristGuideRepository.deleteTouristAttraction(name);
    }


    public void updateTouristAttraction(TouristAttraction touristAttraction){
        touristGuideRepository.updateTouristAttraction(touristAttraction);
    }


    public void createTouristAttraction(TouristAttraction touristAttraction){
        touristGuideRepository.addTouristAttraction(touristAttraction);
    }

    public ArrayList<String> getTags(String name){
        return touristGuideRepository.getTags(name);
    }

    public List<TouristAttraction> getTouristAttractions(){
        return touristGuideRepositoryDB.getTouristAttractions();
    }

    public List<String> getTagsList(){
        return touristGuideRepositoryDB.getTagsList();
    }
}



