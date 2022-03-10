package org.example.exception;

public class AppointmentNotFoundException extends Exception {
    public AppointmentNotFoundException() {
        super("No such appointment");
    }
}
