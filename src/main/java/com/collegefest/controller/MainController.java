package com.collegefest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    
    @GetMapping("/")
    public String home() {
        return "Home";
    }
    
    @GetMapping("/redirectToLogin")
    public String redirectToLogin(@RequestParam String role) {
        switch (role) {

            case "admin": return "redirect:/admin/login";
            case "organizer": return "redirect:/organizer/login";
            case "participant": return "redirect:/participant/login";
            case "volunteer":return "redirect:/volunteer/login";
            default: return "redirect:/";
        }
    }

}

