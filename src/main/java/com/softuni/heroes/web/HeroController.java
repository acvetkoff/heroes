package com.softuni.heroes.web;

import com.softuni.heroes.model.binding.HeroCreateBindingModel;
import com.softuni.heroes.model.entity.enums.Class;
import com.softuni.heroes.model.service.HeroServiceModel;
import com.softuni.heroes.model.view.HeroDetailsViewModel;
import com.softuni.heroes.model.view.HeroViewModel;
import com.softuni.heroes.service.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/heroes")
public class HeroController {
    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroController(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String create(HttpSession session, Model model) {
        List<String> classes = Arrays.stream(Class.values())
                .map(Enum::name)
                .collect(Collectors.toList());


        model.addAttribute("classes", classes);
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        return "create-hero";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("heroCreateBindingModel") HeroCreateBindingModel heroCreateBindingModel,
                             Model model) {

        this.heroService
                .create(this.modelMapper.map(heroCreateBindingModel, HeroServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        HeroDetailsViewModel hero = this.heroService.findById(id);
        model.addAttribute("hero", hero);

        return "details-hero";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        this.heroService.delete(id);

        return "redirect:/";
    }
}
