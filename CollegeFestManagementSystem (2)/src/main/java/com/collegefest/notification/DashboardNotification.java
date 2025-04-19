package com.collegefest.notification;

import org.springframework.stereotype.Component;

 import com.collegefest.model.NotificationEntity;
 import com.collegefest.repository.NotificationRepository;

@Component public class DashboardNotification implements Notification {

    private final NotificationRepository notificationRepository;

    public DashboardNotification(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void send(String recipientUsername, String message) {
        NotificationEntity notification = new NotificationEntity();
        notification.setRecipientUsername(recipientUsername);
        notification.setMessage(message);
        notificationRepository.save(notification);
    }
}