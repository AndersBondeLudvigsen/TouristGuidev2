package com.example.touristguidev2.controller;

import com.example.touristguidev2.model.TouristAttraction;
import com.example.touristguidev2.service.TouristGuideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("attractions")
public class TouristGuideController {
    private TouristGuideService touristGuideService;

    public TouristGuideController(TouristGuideService touristGuideService){
        this.touristGuideService = touristGuideService;
    }

    @GetMapping("")
    public String getAllTouristAttraction(Model model){
        model.addAttribute(touristGuideService.getTouristAttractions());
        return "attractions-list";
    }

    @GetMapping("create")
    public String createTouristAttraction(Model model){
        model.addAttribute("touristattraction", new TouristAttraction());
        return "create-attraction";
    }

    @PostMapping("create")
    public String createTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristGuideService.createTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

        @GetMapping("/{name}/delete")
    public String deleteTouristAttraction(@PathVariable("name") String name) {
        touristGuideService.deleteTouristAttraction(name);
        return "redirect:/attractions";
    }

    @GetMapping("{name}/update")
    public String showUpdateAttraction(@PathVariable("name") String name, Model model) {
        TouristAttraction touristAttraction = touristGuideService.getTouristAttraction(name);
        model.addAttribute("touristAttraction", touristAttraction);
        return "update-attraction";
    }
    @PostMapping("update")
    public String updateProduct(@ModelAttribute TouristAttraction touristAttraction) {
        touristGuideService.updateTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }
    @GetMapping("/{name}/tags")
    public String getTags(@PathVariable("name") String name ,Model model){
        model.addAttribute("name", name);
        model.addAttribute("tags", touristGuideService.getTags(name));
        return "attraction-tags";
    }


}
