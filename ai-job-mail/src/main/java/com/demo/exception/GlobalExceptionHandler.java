package com.demo.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
 
@RestControllerAdvice
public class GlobalExceptionHandler {
 
    // Handles @Valid / @NotBlank / @Email failures
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));
 
        return buildError(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);
    }
 
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }
 
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateResourceException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), null);
    }
 
    @ExceptionHandler(GroqApiException.class)
    public ResponseEntity<Map<String, Object>> handleGroqApi(GroqApiException ex) {
        return buildError(HttpStatus.BAD_GATEWAY, "AI service error: " + ex.getMessage(), null);
    }
 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", null);
    }
 
    private ResponseEntity<Map<String, Object>> buildError(
            HttpStatus status, String message, Object details) {
 
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", message);
        if (details != null) body.put("details", details);
        return ResponseEntity.status(status).body(body);
    }
}