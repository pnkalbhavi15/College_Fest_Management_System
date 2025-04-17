package com.collegefest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "home"; // loads home.html
    }

    @GetMapping("/redirectToLogin")
    public String redirectToLogin(@RequestParam("role") String role) {
        switch (role) {
            case "admin":
                return "redirect:/admin/login";
            case "organizer":
                return "redirect:/organizer/login";
            default:
                return "redirect:/"; // fallback to homepage
        }
    }
}
