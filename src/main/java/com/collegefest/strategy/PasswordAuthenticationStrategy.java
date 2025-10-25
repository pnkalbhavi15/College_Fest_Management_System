package com.collegefest.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collegefest.model.Volunteer;
import com.collegefest.repository.VolunteerRepository;

@Component
public class PasswordAuthenticationStrategy implements AuthenticationStrategy {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public boolean authenticate(String username, String password) {
        // Example alternative authentication logic
        Volunteer volunteer = volunteerRepository.findByUsername(username);
        if (volunteer != null) {
            // Simple password check (in real app, use hashed passwords)
            return volunteer.getPassword().equals(password);
        }
        return false;
    }
}
