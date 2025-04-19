package com.collegefest.notification;

public class EmailNotification implements Notification {
    @Override
    public void send(String to, String message) {
        System.out.println("📧 Sending EMAIL to " + to + ": " + message);
        // Optional: Add real email logic using JavaMailSender
    }

}