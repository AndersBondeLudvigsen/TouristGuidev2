package com.example.touristguidev2.repository;

import com.example.touristguidev2.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristGuideRepositoryTest {

    private TouristGuideRepository touristGuideRepository= new TouristGuideRepository();

    @Test
    void getTagsList() {
        int actualsize = touristGuideRepository.getTagsList().size();
        int expectedsize = 6;
        assertEquals(expectedsize,actualsize);
    }


    @Test
    void getTouristAttraction() {
        String expected = "Brøndby Stadion";
        String actual = touristGuideRepository.getTouristAttraction("Brøndby Stadion").getName();
        assertEquals(expected,actual);
    }


    @Test
    void updateTouristAttraction() {

    }

    @Test
    void addTouristAttraction() {
    }

    @Test
    void deleteTouristAttraction() {
    }

    @Test
    void getTags() {
    }

    @Test
    void getTouristAttractions() {
    }
}