package com.softuni.heroes.web;

import com.softuni.heroes.model.view.HeroViewModel;
import com.softuni.heroes.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private final HeroService heroService;

    @Autowired
    public HomeController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "index";
        }

        List<HeroViewModel> allHeroes = this.heroService.getAll();

        model.addAttribute("heroes", allHeroes);

       return "home";
    }
}
