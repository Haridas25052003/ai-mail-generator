package com.demo.exception;
 
// ── Thrown when trying to register an already-existing email ─────────────────
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
 