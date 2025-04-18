package com.collegefest.repository;

import com.collegefest.model.ParticipantEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantEventRepository extends JpaRepository<ParticipantEvent, Long> {
    // Optionally add custom queries here
}
