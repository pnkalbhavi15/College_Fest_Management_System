package com.collegefest.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

        Set<String> organizers = allEvents.stream()
            .map(e -> e.getOrganizer().getUsername())
            .collect(Collectors.toCollection(TreeSet::new));

        model.addAttribute("events", allEvents);
        model.addAttribute("organizers", organizers);
        return "admin_all_events";
    }


    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        List<Event> approvedEvents = eventRepository.findByStatus("APPROVED");
        model.addAttribute("events", approvedEvents);
        return "admin_calendar";
    }

    @GetMapping("/reports")
    public String viewReports(Model model) {
        List<Event> allEvents = eventRepository.findAll();

        // Count by status
        long approvedCount = allEvents.stream().filter(e -> "APPROVED".equals(e.getStatus())).count();
        long pendingCount = allEvents.stream().filter(e -> "PENDING".equals(e.getStatus())).count();
        long rejectedCount = allEvents.stream().filter(e -> "REJECTED".equals(e.getStatus())).count();

        // Count events per organizer
        Map<String, Long> organizerCounts = allEvents.stream()
                .filter(e -> e.getOrganizer() != null)
                .collect(Collectors.groupingBy(e -> e.getOrganizer().getUsername(), Collectors.counting()));

        model.addAttribute("approvedCount", approvedCount);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("rejectedCount", rejectedCount);
        model.addAttribute("organizerCounts", organizerCounts);

        return "admin_reports";
    }



}
