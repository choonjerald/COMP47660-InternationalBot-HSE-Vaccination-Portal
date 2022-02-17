package org.example.exception;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException() {
        super("No such account");
    }
}
