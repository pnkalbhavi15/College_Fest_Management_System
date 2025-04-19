package com.collegefest.repository;

import com.collegefest.model.Task;
import com.collegefest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEvent(Event event);
}
