package com.collegefest.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component public class NotificationFactory {
    @Autowired
    private DashboardNotification dashboardNotification;

    public Notification createNotification(String type) {
        if ("dashboard".equalsIgnoreCase(type)) {
            return dashboardNotification;
        }

        throw new IllegalArgumentException("Unsupported notification type: " + type);
    }

}
