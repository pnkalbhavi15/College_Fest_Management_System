package com.collegefest.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collegefest.model.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> 
{ 
    List<NotificationEntity> findByRecipientUsernameOrderByTimestampDesc(String recipientUsername); 
}