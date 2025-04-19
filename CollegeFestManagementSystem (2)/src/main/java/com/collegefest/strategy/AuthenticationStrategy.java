package com.collegefest.strategy;

public interface AuthenticationStrategy {
    boolean authenticate(String username, String password);
}
