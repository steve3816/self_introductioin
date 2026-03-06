package com.example.selfintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.example.selfintro.util.ValidationUtils;

import com.example.selfintro.dto.RegisterRequest;
import com.example.selfintro.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")

    public String registerForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterRequest registerRequest, BindingResult result, Model model) {
        if (ValidationUtils.hasErrorsAndPopulate(result, model)) {
            return "register";
        }
        try {
            userService.registerUser(registerRequest);
            return "redirect:/login?registered=true";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("registerRequest", registerRequest);
            return "register";
        }
    }

}
