package com.collegefest.repository;

import com.collegefest.model.Task;
import com.collegefest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t JOIN FETCH t.event")
    List<Task> findByEvent(Event event);
}
