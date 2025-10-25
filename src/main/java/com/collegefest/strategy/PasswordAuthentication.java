package com.collegefest.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collegefest.model.Volunteer;
import com.collegefest.repository.VolunteerRepository;

@Component
public class PasswordAuthentication implements AuthenticationStrategy {

    private VolunteerRepository volunteerRepository;

    @Autowired
    public void setVolunteerRepository(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Volunteer volunteer = volunteerRepository.findByUsernameAndPassword(username, password);
        return volunteer != null;
    }
}
