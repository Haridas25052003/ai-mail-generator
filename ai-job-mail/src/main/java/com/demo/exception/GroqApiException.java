package com.demo.exception;
 
// ── Thrown when the Groq API call fails ──────────────────────────────────────
public class GroqApiException extends RuntimeException {
    public GroqApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
 