package com.demo.exception;
 
// ── Thrown when a requested resource (User, Email etc.) is not found ──────────
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
 