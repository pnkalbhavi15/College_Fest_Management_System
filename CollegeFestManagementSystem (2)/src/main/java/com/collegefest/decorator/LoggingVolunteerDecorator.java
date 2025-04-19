package com.collegefest.decorator;

import com.collegefest.model.Volunteer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingVolunteerDecorator extends VolunteerDecorator {

    private static final Logger logger = LoggerFactory.getLogger(LoggingVolunteerDecorator.class);

    public LoggingVolunteerDecorator(Volunteer decoratedVolunteer) {
        super(decoratedVolunteer);
    }

    @Override
    public Long getId() {
        logger.info("Getting volunteer ID: {}", decoratedVolunteer.getId());
        return decoratedVolunteer.getId();
    }

    @Override
    public void setId(Long id) {
        logger.info("Setting volunteer ID: {}", id);
        decoratedVolunteer.setId(id);
    }

    @Override
    public String getUsername() {
        logger.info("Getting volunteer username: {}", decoratedVolunteer.getUsername());
        return decoratedVolunteer.getUsername();
    }

    @Override
    public void setUsername(String username) {
        logger.info("Setting volunteer username: {}", username);
        decoratedVolunteer.setUsername(username);
    }

    @Override
    public String getPassword() {
        logger.info("Getting volunteer password");
        return decoratedVolunteer.getPassword();
    }

    @Override
    public void setPassword(String password) {
        logger.info("Setting volunteer password");
        decoratedVolunteer.setPassword(password);
    }

    @Override
    public String getEmail() {
        logger.info("Getting volunteer email: {}", decoratedVolunteer.getEmail());
        return decoratedVolunteer.getEmail();
    }

    @Override
    public void setEmail(String email) {
        logger.info("Setting volunteer email: {}", email);
        decoratedVolunteer.setEmail(email);
    }

    @Override
    public String getPhoneNumber() {
        logger.info("Getting volunteer phone number: {}", decoratedVolunteer.getPhoneNumber());
        return decoratedVolunteer.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        logger.info("Setting volunteer phone number: {}", phoneNumber);
        decoratedVolunteer.setPhoneNumber(phoneNumber);
    }

    @Override
    public String getDepartment() {
        logger.info("Getting volunteer department: {}", decoratedVolunteer.getDepartment());
        return decoratedVolunteer.getDepartment();
    }

    @Override
    public void setDepartment(String department) {
        logger.info("Setting volunteer department: {}", department);
        decoratedVolunteer.setDepartment(department);
    }
}
