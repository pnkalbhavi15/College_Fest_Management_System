package com.collegefest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collegefest.model.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Organizer findByUsernameAndPassword(String username, String password);
    Organizer findByUsername(String username);
}
