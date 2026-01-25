package com.demo.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final String URL = "https://api.groq.com/openai/v1/chat/completions";

    public String generateEmail(String name, String position, String company, String jd) {

        String prompt = """
        Write a professional job application email.

        Candidate Name: %s
        Job Role: %s
        Company: %s
        Job Description: %s

        The email must be polite, confident and well formatted.
        """.formatted(name, position, company, jd);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> message = Map.of(
                "role", "user",
                "content", prompt
        );

        Map<String, Object> body = Map.of(
        	    "model", "llama-3.3-70b-versatile",
        	    "messages", List.of(
        	        Map.of("role", "system", "content", "You are a professional job application email writer."),
        	        Map.of("role", "user", "content", prompt)
        	    ),
        	    "temperature", 0.7
        	);




        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(URL, request, Map.class);

        List<Map> choices = (List<Map>) response.getBody().get("choices");
        Map msg = (Map) choices.get(0).get("message");

        return msg.get("content").toString();
    }
}
