package com.collegefest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collegefest.model.Event;
import com.collegefest.model.NotificationEntity;
import com.collegefest.model.Organizer;
import com.collegefest.repository.EventRepository;
import com.collegefest.repository.NotificationRepository;
import com.collegefest.repository.OrganizerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private OrganizerRepository organizerRepo;

    @Autowired
    private NotificationRepository notificationRepository;


    @GetMapping("/dashboard")
    public String organizerDashboard(HttpSession session, Model model) {
        Organizer organizer = (Organizer) session.getAttribute("loggedInOrganizer");
        if (organizer == null) {
            return "redirect:/organizer/login";
        }

        List<Event> events = eventRepo.findByOrganizer(organizer);
        model.addAttribute("organizer", organizer);
        model.addAttribute("events", events);
        List<NotificationEntity> notifications = notificationRepository.findByRecipientUsernameOrderByTimestampDesc(organizer.getUsername());
        model.addAttribute("notifications", notifications);
        return "organizer-dashboard";
    }

    @GetMapping("/events/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "event-form";
    }

    @PostMapping("/events/save")
    public String saveEvent(@ModelAttribute Event event, HttpSession session) {
        try {
            Organizer organizer = (Organizer) session.getAttribute("loggedInOrganizer");

            if (organizer == null) {
                System.out.println("❌ Organizer not in session");
                return "redirect:/organizer/login";
            }

            event.setOrganizer(organizer);
            event.setStatus("Pending");

            System.out.println("📅 Received date: " + event.getDate());
            eventRepo.save(event);
        } catch (Exception e) {
            e.printStackTrace(); // Watch console for binding or DB errors
        }

        return "redirect:/organizer/dashboard";
    }

    @GetMapping("/edit-event/{id}")
    public String showEditEventForm(@PathVariable Long id, Model model, HttpSession session) {
        Event event = eventRepo.findById(id).orElse(null);
        Organizer organizer = (Organizer) session.getAttribute("loggedInOrganizer");

        if (event == null || organizer == null || !event.getOrganizer().getId().equals(organizer.getId())) {
            return "redirect:/organizer/dashboard";
        }

        model.addAttribute("event", event);
        return "organizer_edit_events";
    }

    @PostMapping("/update-event")
    public String updateEvent(@ModelAttribute Event event, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("loggedInOrganizer");
        if (organizer == null) return "redirect:/organizer/login";

        Event existing = eventRepo.findById(event.getId()).orElse(null);
        if (existing != null && existing.getOrganizer().getId().equals(organizer.getId())) {
            existing.setTitle(event.getTitle());
            existing.setDescription(event.getDescription());
            existing.setDate(event.getDate());
            existing.setVenue(event.getVenue());
            eventRepo.save(existing);
        }

        return "redirect:/organizer/dashboard";
    }

    @GetMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable Long id, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("loggedInOrganizer");
        Event event = eventRepo.findById(id).orElse(null);

        if (event != null && organizer != null && event.getOrganizer().getId().equals(organizer.getId())) {
            eventRepo.delete(event);
        }

        return "redirect:/organizer/dashboard";
    }
}
