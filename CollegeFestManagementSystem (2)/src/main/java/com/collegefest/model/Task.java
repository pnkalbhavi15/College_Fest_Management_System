package com.collegefest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String title;  // New title property

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // Default Constructor
    public Task() {
    }

    // Constructor with parameters
    public Task(Long id, String name, String description, String title, Event event) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.title = title;  // Initialize title
        this.event = event;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;  // Getter for title
    }

    public void setTitle(String title) {
        this.title = title;  // Setter for title
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
