package com.collegefest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collegefest.model.Event;
import com.collegefest.model.Organizer;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizer(Organizer organizer);
    List<Event> findByStatus(String status);

}
