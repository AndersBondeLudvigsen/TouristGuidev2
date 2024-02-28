package com.example.touristguidev2.controller;

import com.example.touristguidev2.model.TouristAttraction;
import com.example.touristguidev2.service.TouristGuideService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristGuideController.class)
class TouristGuideControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristGuideService touristGuideService;

    private TouristAttraction touristAttraction = new TouristAttraction();


    @Test
    void getAllTouristAttraction() throws Exception{
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractions-list"));

    }

//TODO create virker ikke
  /*  @Test
    void createTouristAttraction() throws Exception {
        mockMvc.perform(get("/attractions/create"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("touristattraction", touristAttraction))
                .andExpect(view().name("create-attraction"));
    }
*/

    @Test
    void deleteTouristAttraction() {
    }

    @Test
    void showUpdateAttraction() {
    }

    @Test
    void updateTouristAttraction() {
    }

    @Test
    void getTags() throws Exception{
        mockMvc.perform(get("/attractions/Parken/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-tags"))
                .andExpect(content().string(containsString("Parken")));

    }
}