package com.demo.service;

import com.demo.dto.EmailGenerateResponse;
import com.demo.exception.GroqApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String MODEL    = "llama-3.3-70b-versatile";

    // Singleton RestTemplate — creating a new one per call is wasteful
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Calls Groq and returns a structured response with subject + body parsed out.
     */
    public EmailGenerateResponse generateEmail(
            String candidateName,
            String position,
            String company,
            String jd) {

        String prompt = buildPrompt(candidateName, position, company, jd);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = Map.of(
                "model", MODEL,
                "messages", List.of(
                        Map.of("role", "system", "content",
                                """
                                You are a professional job application email writer.
                                Always respond in this exact format:
                                SUBJECT: <email subject line>
                                BODY:
                                <full email body>
                                """),
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.7,
                "max_tokens", 1024
        );

        try {
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(GROQ_URL, entity, Map.class);

            String rawContent = extractContent(response.getBody());
            return parseEmailResponse(rawContent);

        } catch (RestClientException ex) {
            log.error("Groq API call failed: {}", ex.getMessage());
            throw new GroqApiException("Failed to generate email via Groq", ex);
        }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private String buildPrompt(String name, String position, String company, String jd) {
        return """
            Write a professional cold job application email.
 
            Candidate Name : %s
            Applying For   : %s
            Company        : %s
            Job Description: %s
 
            Rules:
            - Keep it concise (under 200 words)
            - Professional but personable tone
            - Include a clear call to action
            """.formatted(name, position, company, jd);
    }

    @SuppressWarnings("unchecked")
    private String extractContent(Map<?, ?> responseBody) {
        if (responseBody == null) throw new GroqApiException("Empty response from Groq", null);
        List<Map<?, ?>> choices = (List<Map<?, ?>>) responseBody.get("choices");
        Map<?, ?> msg = (Map<?, ?>) choices.get(0).get("message");
        return msg.get("content").toString();
    }

    /**
     * Parses the structured SUBJECT / BODY format from the model response.
     * Falls back gracefully if the model doesn't follow the format.
     */
    private EmailGenerateResponse parseEmailResponse(String raw) {
        String subject = "Job Application";
        String body    = raw;

        if (raw.contains("SUBJECT:") && raw.contains("BODY:")) {
            int subjectStart = raw.indexOf("SUBJECT:") + "SUBJECT:".length();
            int bodyStart    = raw.indexOf("BODY:")    + "BODY:".length();
            subject = raw.substring(subjectStart, raw.indexOf("BODY:")).strip();
            body    = raw.substring(bodyStart).strip();
        }

        return new EmailGenerateResponse(subject, body, LocalDateTime.now());
    }
}