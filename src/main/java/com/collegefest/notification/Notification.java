package com.collegefest.notification;

public interface Notification {
    void send(String recipientUsername, String message);
}
