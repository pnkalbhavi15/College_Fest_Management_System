package com.collegefest.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.collegefest.model.Attendance;
import com.collegefest.model.Event;
import com.collegefest.model.Volunteer;
import com.collegefest.repository.AttendanceRepository;
import com.collegefest.repository.EventRepository;
import com.collegefest.repository.VolunteerRepository;
import com.collegefest.strategy.AuthenticationStrategy;

import jakarta.servlet.http.HttpSession;

@Controller
public class VolunteerController {
    
    @Autowired
    private VolunteerRepository volunteerRepo;

    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AttendanceRepository attendanceRepo;
    

    @GetMapping("/volunteer/login")
    public String loginPage() {
        return "volunteer_login";
    }
    
    @GetMapping("/volunteer/dashboard/attendance")
    public String attendancePage(HttpSession session, Model model) {
        Volunteer volunteer = (Volunteer) session.getAttribute("loggedInVolunteer");
        if (volunteer == null) {
            return "redirect:/volunteer/login";
        }
        
        List<Attendance> attendanceRecords = attendanceRepo.findByVolunteer(volunteer);
        model.addAttribute("attendanceRecords", attendanceRecords);
        model.addAttribute("volunteer", volunteer);
        return "volunteer_attendance";
    }
    @PostMapping("/volunteer/check-in")
    public String checkIn(@RequestParam Long eventId, HttpSession session) {
        Volunteer volunteer = (Volunteer) session.getAttribute("loggedInVolunteer");
        if (volunteer != null) {
            try {
                Volunteer managedVolunteer = volunteerRepo.findById(volunteer.getId()).orElse(null);
                if (managedVolunteer == null) {
                    return "redirect:/volunteer/login";
                }
                Attendance attendance = new Attendance();
                attendance.setVolunteer(managedVolunteer);
                Event event = eventRepository.findById(eventId).orElse(null);
                if (event == null) {
                    return "redirect:/volunteer/dashboard";
                }
                attendance.setEvent(event);
                attendance.setCheckInTime(LocalDateTime.now());
                attendance.setStatus("PRESENT");
                attendanceRepo.save(attendance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/volunteer/dashboard/attendance";
    }
    @PostMapping("/volunteer/check-out")
    public String checkOut(@RequestParam Long eventId, HttpSession session) {
        Volunteer volunteer = (Volunteer) session.getAttribute("loggedInVolunteer");
        if (volunteer != null) {
            try {
                Volunteer managedVolunteer = volunteerRepo.findById(volunteer.getId()).orElse(null);
                if (managedVolunteer == null) {
                    return "redirect:/volunteer/login";
                }
                List<Attendance> records = attendanceRepo.findByVolunteerAndCheckOutTimeIsNull(managedVolunteer);
                Attendance attendanceForEvent = null;
                for (Attendance att : records) {
                    if (att.getEvent() != null && att.getEvent().getId().equals(eventId)) {
                        attendanceForEvent = att;
                        break;
                    }
                }
                if (attendanceForEvent != null) {
                    attendanceForEvent.setCheckOutTime(LocalDateTime.now());
                    attendanceRepo.save(attendanceForEvent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/volunteer/dashboard/attendance";
    }

    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("basicAuthenticationStrategy")
    private AuthenticationStrategy authenticationStrategy;

    private Volunteer decorateVolunteer(Volunteer volunteer) {
        return new com.collegefest.decorator.LoggingVolunteerDecorator(volunteer);
    }

    @PostMapping("/volunteer/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       Model model,
                       HttpSession session) {
        if (authenticationStrategy.authenticate(username, password)) {
            Volunteer volunteer = volunteerRepo.findByUsernameAndPassword(username, password);
            volunteer = decorateVolunteer(volunteer);
            session.setAttribute("loggedInVolunteer", volunteer);
            return "redirect:/volunteer/dashboard";
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "volunteer_login";
        }
    }
    
    @GetMapping("/volunteer/signup")
    public String signupPage() {
        return "Volunteer_signup";
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
            return "Volunteer_signup";
        }
        
        Volunteer volunteer = new Volunteer();
        volunteer.setUsername(username);
        volunteer.setPassword(password);
        volunteer.setEmail(email);
        volunteer.setPhoneNumber(phoneNumber);
        volunteer.setDepartment(department);
        
        volunteerRepo.save(volunteer);
        model.addAttribute("success", "Account created! Please login.");
        return "Volunteer_login";
    }

    
    
    @GetMapping("/volunteer/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Volunteer volunteer = (Volunteer) session.getAttribute("loggedInVolunteer");
        if (volunteer == null) {
            return "redirect:/volunteer/login";
        }
        
        List<Event> approvedEvents = eventRepository.findByStatus("APPROVED");
        model.addAttribute("volunteer", volunteer);
        model.addAttribute("events", approvedEvents);
        return "Volunteer_dashboard";
    }
    
    @GetMapping("/volunteer/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/volunteer/login?logout";
    }
}