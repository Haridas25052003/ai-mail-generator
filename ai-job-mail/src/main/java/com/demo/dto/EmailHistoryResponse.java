package com.demo.dto;
 
import lombok.AllArgsConstructor;
import lombok.Getter;
 
import java.time.LocalDateTime;
 
// ── Outbound DTO for /api/email/history/{userId} ──────────────────────────────
@Getter
@AllArgsConstructor
public class EmailHistoryResponse {
    private Long id;
    private String company;
    private String position;
    private String subject;
    private String body;
    private LocalDateTime generatedAt;
}