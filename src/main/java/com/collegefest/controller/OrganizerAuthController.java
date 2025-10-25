package com.collegefest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.collegefest.model.Organizer;
import com.collegefest.repository.OrganizerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrganizerAuthController {

    @Autowired
    private OrganizerRepository organizerRepository;

    @GetMapping("/organizer/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("organizer", new Organizer());
        return "organizer_signup";
    }

    @PostMapping("/organizer/signup")
    public String processSignup(@ModelAttribute Organizer organizer) {
        // NOTE: Hash password here ideally
        organizerRepository.save(organizer);
        return "redirect:/organizer/login";
    }

    @GetMapping("/organizer/login")
    public String showLoginForm(Model model) {
        model.addAttribute("organizer", new Organizer());
        return "organizer_login";
    }

    @PostMapping("/organizer/login")
    public String processLogin(@ModelAttribute Organizer organizer, Model model, HttpSession session) {
        Organizer loggedIn = organizerRepository.findByUsernameAndPassword(
                organizer.getUsername(), organizer.getPassword()
        );

        if (loggedIn != null) {
            session.setAttribute("loggedInOrganizer", loggedIn); // 🔐 Store in session
            return "redirect:/organizer/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "organizer_login";
        }
    }

    @GetMapping("/organizer/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 🚪 Clear session
        return "redirect:/organizer/login";
    }
}
