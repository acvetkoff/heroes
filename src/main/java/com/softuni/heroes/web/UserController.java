package com.softuni.heroes.web;

import com.softuni.heroes.model.binding.UserLoginBindingModel;
import com.softuni.heroes.model.binding.UserRegisterBindingModel;
import com.softuni.heroes.model.service.UserServiceModel;
import com.softuni.heroes.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(
            @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordMismatch", true);
            redirectAttributes.addFlashAttribute("passwordError", "Confirm password mismatch!");

            return "redirect:register";
        }

        try {
            this.userService
                    .register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("userExists", true);
            redirectAttributes.addFlashAttribute("errorMessage", "User with that username already exists!");

            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) {
        UserServiceModel user = this.userService.getByName(userLoginBindingModel.getUsername());

        if (user == null) {
            redirectAttributes.addFlashAttribute("notFound", true);
            redirectAttributes.addFlashAttribute("errorMessage", "No user with that username!");

            return "redirect:login";
        }

        session.setAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
