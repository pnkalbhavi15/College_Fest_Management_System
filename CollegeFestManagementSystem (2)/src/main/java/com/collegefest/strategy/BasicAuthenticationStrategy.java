package com.collegefest.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collegefest.model.Volunteer;
import com.collegefest.repository.VolunteerRepository;

@Component
public class BasicAuthenticationStrategy implements AuthenticationStrategy {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public boolean authenticate(String username, String password) {
        Volunteer volunteer = volunteerRepository.findByUsernameAndPassword(username, password);
        return volunteer != null;
    }
}
