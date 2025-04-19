package com.collegefest.repository;

import com.collegefest.model.ParticipantEvent;
import com.collegefest.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantEventRepository extends JpaRepository<ParticipantEvent, Long> {
    List<ParticipantEvent> findByParticipant(Participant participant);
}
