package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {



    @PostMapping("/home")
    public String home() {
        return "<h1>Good Valid Token</h1>";
    }
}
