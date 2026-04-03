package com.demo.dto;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
 
// ── Inbound DTO for /auth/register and /auth/login ───────────────────────────
@Getter
@Setter
public class AuthRequest {
 
    @NotBlank(message = "Name is required")
    private String name;
 
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}
 