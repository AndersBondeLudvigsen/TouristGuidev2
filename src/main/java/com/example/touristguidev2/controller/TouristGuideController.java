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
        model.addAttribute("tagsList", touristGuideService.getTagsList());
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
        model.addAttribute("tagsList", touristGuideService.getTagsList());
        return "update-attraction";
    }

    @PostMapping("update")
    public String updateProduct(@ModelAttribute("touristAttraction") TouristAttraction updatedAttraction) {
        TouristAttraction existingAttraction = touristGuideService.getTouristAttraction(updatedAttraction.getName());
        existingAttraction.setDescription(updatedAttraction.getDescription());
        existingAttraction.setTags(updatedAttraction.getTags());
        touristGuideService.updateTouristAttraction(existingAttraction);
        return "redirect:/attractions";
    }


    @GetMapping(value = "{name}/tags")
        public String getTags(@PathVariable String name, Model model){
        model.addAttribute("attractionName", name);
        model.addAttribute("tags", touristGuideService.getTags(name));
        return "attraction-tags";
    }


}
