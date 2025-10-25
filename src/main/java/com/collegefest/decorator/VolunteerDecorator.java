package com.collegefest.decorator;

import com.collegefest.model.Volunteer;

public abstract class VolunteerDecorator extends Volunteer {
    protected Volunteer decoratedVolunteer;

    public VolunteerDecorator(Volunteer decoratedVolunteer) {
        this.decoratedVolunteer = decoratedVolunteer;
    }

    @Override
    public Long getId() {
        return decoratedVolunteer.getId();
    }

    @Override
    public void setId(Long id) {
        decoratedVolunteer.setId(id);
    }

    @Override
    public String getUsername() {
        return decoratedVolunteer.getUsername();
    }

    @Override
    public void setUsername(String username) {
        decoratedVolunteer.setUsername(username);
    }

    @Override
    public String getPassword() {
        return decoratedVolunteer.getPassword();
    }

    @Override
    public void setPassword(String password) {
        decoratedVolunteer.setPassword(password);
    }

    @Override
    public String getEmail() {
        return decoratedVolunteer.getEmail();
    }

    @Override
    public void setEmail(String email) {
        decoratedVolunteer.setEmail(email);
    }

    @Override
    public String getPhoneNumber() {
        return decoratedVolunteer.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        decoratedVolunteer.setPhoneNumber(phoneNumber);
    }

    @Override
    public String getDepartment() {
        return decoratedVolunteer.getDepartment();
    }

    @Override
    public void setDepartment(String department) {
        decoratedVolunteer.setDepartment(department);
    }
}
