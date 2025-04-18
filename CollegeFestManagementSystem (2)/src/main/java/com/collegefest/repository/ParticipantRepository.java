package com.collegefest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.collegefest.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant findByUsername(String username);
    Participant findByUsernameAndPassword(String username, String password);
}
