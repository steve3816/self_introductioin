package com.example.selfintro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello, Welcome to Self Introduction App! (Authenticated)";
    }

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello from Public Endpoint!";
    }
}
