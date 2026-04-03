package com.demo.dto;
 
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
 
// ── Inbound DTO for /api/email/generate ──────────────────────────────────────
// Never expose JPA entities directly to the API layer.
// DTOs decouple your API contract from your DB schema.
@Getter
@Setter
public class EmailGenerateRequest {
 
    @NotBlank(message = "Candidate name is required")
    private String candidateName;
 
    @NotBlank(message = "Company is required")
    private String company;
 
    @NotBlank(message = "Designation is required")
    private String designation;
 
    @NotBlank(message = "Job description is required")
    private String jobDescription;
 
    private Long userId;   // optional: if provided, email is saved to history
}