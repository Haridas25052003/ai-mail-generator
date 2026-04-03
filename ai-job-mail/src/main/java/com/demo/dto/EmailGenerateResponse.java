package com.demo.dto;
 
import lombok.AllArgsConstructor;
import lombok.Getter;
 
import java.time.LocalDateTime;
 
// ── Outbound DTO for generated email ─────────────────────────────────────────
@Getter
@AllArgsConstructor
public class EmailGenerateResponse {
    private String subject;
    private String body;
    private LocalDateTime generatedAt;
}