package com.demo.dto;
 
import lombok.AllArgsConstructor;
import lombok.Getter;
 
// ── Outbound DTO for auth responses ──────────────────────────────────────────
// Never return the raw User entity (exposes password field, internal IDs, etc.)
@Getter
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String name;
    private String email;
}