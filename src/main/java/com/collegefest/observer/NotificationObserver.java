package com.collegefest.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collegefest.model.NotificationEntity;
import com.collegefest.repository.NotificationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotificationObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationObserver.class);

    @Autowired
    private NotificationRepository notificationRepository;

    private String recipientUsername;

    public NotificationObserver() {
        // Default constructor for Spring
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
        logger.info("Recipient username set to: {}", recipientUsername);
    }

    @Override
    public void update(String message) {
        logger.info("NotificationObserver update called for user: {} with message: {}", recipientUsername, message);
        NotificationEntity notification = new NotificationEntity();
        notification.setRecipientUsername(recipientUsername);
        notification.setMessage(message);
        notificationRepository.save(notification);
        logger.info("Notification saved to repository for user: {}", recipientUsername);
    }
}
