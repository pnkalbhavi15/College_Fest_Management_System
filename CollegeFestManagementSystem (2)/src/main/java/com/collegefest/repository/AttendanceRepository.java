package com.collegefest.repository;

import com.collegefest.model.Attendance;
import com.collegefest.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByVolunteer(Volunteer volunteer);
    List<Attendance> findByVolunteerAndCheckOutTimeIsNull(Volunteer volunteer);
}