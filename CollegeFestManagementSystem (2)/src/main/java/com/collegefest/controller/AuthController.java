package com.collegefest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.collegefest.model.Admin;
import com.collegefest.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping("/admin/login")
    public String loginPage() {
        return "admin_login";
    }

    @PostMapping("/admin/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        Admin admin = adminRepo.findByUsernameAndPassword(username, password);
        if (admin != null) {
            session.setAttribute("loggedInAdmin", admin);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "admin_login";
        }
    }


    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String email,
                         Model model) {
        if (adminRepo.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists!");
            return "signup";
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setEmail(email);

        adminRepo.save(admin);
        model.addAttribute("success", "Account created! Please login.");
        return "admin_login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("admin", admin);
        return "admin_dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login?logout";
    }

}
