package com.collegefest.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotificationFactory {

    private static final Logger logger = LoggerFactory.getLogger(NotificationFactory.class);

    @Autowired
    private DashboardNotification dashboardNotification;

    public Notification createNotification(String type) {
        logger.info("Creating notification of type: {}", type);
        if ("dashboard".equalsIgnoreCase(type)) {
            return dashboardNotification;
        }

        logger.error("Unsupported notification type requested: {}", type);
        throw new IllegalArgumentException("Unsupported notification type: " + type);
    }

}
