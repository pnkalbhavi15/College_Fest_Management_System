package com.collegefest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collegefest.model.Event;
import com.collegefest.model.Organizer;
import com.collegefest.repository.EventRepository;
import com.collegefest.repository.OrganizerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private OrganizerRepository organizerRepo;

    @GetMapping("/dashboard")
    public String organizerDashboard(HttpSession session, Model model) {
        Organizer organizer = (Organizer) session.getAttribute("loggedInOrganizer");
        if (organizer == null) {
            return "redirect:/organizer/login";
        }

        List<Event> events = eventRepo.findByOrganizer(organizer);
        model.addAttribute("organizer", organizer);
        model.addAttribute("events", events);
        return "organizer-dashboard";
    }

    @GetMapping("/events/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "event-form";
    }

    @PostMapping("/events/save")
    public String saveEvent(@ModelAttribute Event event, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("loggedInOrganizer");
        if (organizer != null) {
            event.setOrganizer(organizer);
            event.setStatus("Pending");
            eventRepo.save(event);
        }
        return "redirect:/organizer/dashboard";
    }
}
