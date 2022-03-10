package org.example.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Used does not exist.");
    }
}
