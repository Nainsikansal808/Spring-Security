package com.demo.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";  // Assuming you have /home mapped
    }
}
