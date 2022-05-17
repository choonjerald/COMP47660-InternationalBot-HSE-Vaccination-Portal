package org.example.exception;

public class BlockedIPException extends Exception {
    public BlockedIPException() {
        super("IP address blocked for 20 mins.");
    }
}
