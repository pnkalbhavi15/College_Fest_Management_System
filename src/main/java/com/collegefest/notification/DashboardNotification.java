package com.collegefest.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collegefest.model.NotificationEntity;
import com.collegefest.repository.NotificationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DashboardNotification implements Notification {

    private static final Logger logger = LoggerFactory.getLogger(DashboardNotification.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void send(String recipientUsername, String message) {
        logger.info("Sending dashboard notification to {}: {}", recipientUsername, message);
        NotificationEntity notification = new NotificationEntity();
        notification.setRecipientUsername(recipientUsername);
        notification.setMessage(message);
        notificationRepository.save(notification);
        logger.info("Notification saved to repository for user: {}", recipientUsername);
    }
}
