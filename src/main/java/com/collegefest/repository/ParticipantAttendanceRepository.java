package com.collegefest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collegefest.model.Event;
import com.collegefest.model.Participant;
import com.collegefest.model.ParticipantAttendance;

public interface ParticipantAttendanceRepository extends JpaRepository<ParticipantAttendance, Long> {
    List<ParticipantAttendance> findByEvent(Event event);
    ParticipantAttendance findByParticipantAndEvent(Participant participant, Event event);
}