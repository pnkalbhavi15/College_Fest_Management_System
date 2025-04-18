package com.collegefest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collegefest.model.Event;
import com.collegefest.repository.EventRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/pending-events")
    public String viewPendingEvents(Model model) {
        List<Event> pendingEvents = eventRepository.findByStatus("PENDING");
        model.addAttribute("events", pendingEvents);
        return "pending-events";
    }

    @PostMapping("/approve/{eventId}")
    public String approveEvent(@PathVariable Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            event.setStatus("APPROVED");
            eventRepository.save(event);
        }
        return "redirect:/admin/pending-events";
    }

    @PostMapping("/reject/{eventId}")
    public String rejectEvent(@PathVariable Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            event.setStatus("REJECTED");
            eventRepository.save(event);
        }
        return "redirect:/admin/pending-events";
    }

    @GetMapping("/all-events")
    public String viewAllEvents(Model model) {
        List<Event> allEvents = eventRepository.findAll();
        model.addAttribute("events", allEvents);
        return "admin_all_events";
    }

    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        List<Event> approvedEvents = eventRepository.findByStatus("APPROVED");
        model.addAttribute("events", approvedEvents);
        return "admin_calendar";
    }


}
