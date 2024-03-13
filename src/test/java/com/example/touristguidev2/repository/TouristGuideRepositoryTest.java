//package com.example.touristguidev2.repository;
//
//import com.example.touristguidev2.model.TouristAttraction;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TouristGuideRepositoryTest {
//
//    private TouristGuideRepository touristGuideRepository= new TouristGuideRepository();
//
//    @Test
//    void getTagsList() {
//        int actualsize = touristGuideRepository.getTagsList().size();
//        int expectedsize = 6;
//        assertEquals(expectedsize,actualsize);
//    }
//
//
//    @Test
//    void getTouristAttraction() {
//        String expected = "Brøndby Stadion";
//        String actual = touristGuideRepository.getTouristAttraction("Brøndby Stadion").getName();
//        assertEquals(expected,actual);
//    }
//
//
//    @Test
//    void updateTouristAttraction() {
//
//        touristGuideRepository.updateTouristAttraction(new TouristAttraction("Parken", "test", new ArrayList<>(List.of("test"))));
//
//        String expected = "test";
//        String actual = touristGuideRepository.getTouristAttraction("Parken").getDescription();
//
//        assertEquals(expected,actual);
//
//
//    }
//
//    @Test
//    void addTouristAttraction() {
//        TouristAttraction touristAttractionTest = new TouristAttraction("Anders", "Anders plads" , new ArrayList<>(List.of("Dårlig bane")));
//        touristGuideRepository.getTouristAttractions().add(touristAttractionTest);
//
//        int expected = 5;
//        int actual = touristGuideRepository.getTouristAttractions().size();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteTouristAttraction() {
//        touristGuideRepository.deleteTouristAttraction("Parken");
//
//        int expected = 3;
//        int actual = touristGuideRepository.getTouristAttractions().size();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getTags() {
//        int expected = 2;
//        int actual =touristGuideRepository.getTouristAttraction("parken").getTags().size();
//        assertEquals(expected,actual);
//    }
//
//
//    @Test
//    void getTouristAttractions() {
//        int expected = 4;
//        int actual = touristGuideRepository.getTouristAttractions().size();
//        assertEquals(expected,actual);
//
//    }
//}