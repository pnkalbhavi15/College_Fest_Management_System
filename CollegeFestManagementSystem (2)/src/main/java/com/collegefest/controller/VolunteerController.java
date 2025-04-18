package com.collegefest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.collegefest.model.Volunteer;
import com.collegefest.repository.VolunteerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class VolunteerController {
    
    @Autowired
    private VolunteerRepository volunteerRepo;
    
    @GetMapping("/volunteer/login")
    public String loginPage() {
        return "volunteer_login";
    }
    
    @PostMapping("/volunteer/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        Volunteer volunteer = volunteerRepo.findByUsernameAndPassword(username, password);
        if (volunteer != null) {
            session.setAttribute("loggedInVolunteer", volunteer);
            return "redirect:/volunteer/dashboard";
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "volunteer_login";
        }
    }
    
    @GetMapping("/volunteer/signup")
    public String signupPage() {
        return "volunteer_signup";
    }
    
    @PostMapping("/volunteer/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String email,
                         @RequestParam(required = false) String phoneNumber,
                         @RequestParam(required = false) String department,
                         Model model) {
        if (volunteerRepo.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists!");
            return "volunteer_signup";
        }
        
        Volunteer volunteer = new Volunteer();
        volunteer.setUsername(username);
        volunteer.setPassword(password);
        volunteer.setEmail(email);
        volunteer.setPhoneNumber(phoneNumber);
        volunteer.setDepartment(department);
        
        volunteerRepo.save(volunteer);
        model.addAttribute("success", "Account created! Please login.");
        return "volunteer_login";
    }
    
    

    @GetMapping("/volunteer/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Volunteer volunteer = (Volunteer) session.getAttribute("loggedInVolunteer");
        if (volunteer == null) {
            return "redirect:/volunteer/login";
        }
        model.addAttribute("volunteer", volunteer);
        return "volunteer_dashboard";
    }
    
    @GetMapping("/volunteer/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/volunteer/login?logout";
    }
}