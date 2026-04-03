package com.demo.service;

import com.demo.dto.EmailGenerateRequest;
import com.demo.dto.EmailGenerateResponse;
import com.demo.dto.EmailHistoryResponse;
import com.demo.exception.ResourceNotFoundException;
import com.demo.model.EmailRequest;
import com.demo.model.GeneratedEmail;
import com.demo.model.User;
import com.demo.repository.EmailRequestRepository;
import com.demo.repository.GeneratedEmailRepository;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final GroqService              groqService;
    private final EmailRequestRepository   emailRequestRepository;
    private final GeneratedEmailRepository generatedEmailRepository;
    private final UserRepository           userRepository;

    /**
     * Core method: generate email via Groq and optionally persist it
     * if a userId is provided in the request.
     */
    @Transactional
    public EmailGenerateResponse generateAndSave(EmailGenerateRequest dto) {

        // 1. Call Groq
        EmailGenerateResponse generated = groqService.generateEmail(
                dto.getCandidateName(),
                dto.getDesignation(),
                dto.getCompany(),
                dto.getJobDescription()
        );

        // 2. Persist only if user is logged in (userId provided)
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "User not found: " + dto.getUserId()));

            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setPosition(dto.getDesignation());
            emailRequest.setCompany(dto.getCompany());
            emailRequest.setJobDescription(dto.getJobDescription());
            emailRequest.setUser(user);
            emailRequestRepository.save(emailRequest);

            GeneratedEmail savedEmail = new GeneratedEmail();
            savedEmail.setSubject(generated.getSubject());
            savedEmail.setBody(generated.getBody());
            savedEmail.setEmailRequest(emailRequest);
            generatedEmailRepository.save(savedEmail);

            log.info("Email saved to history for userId={}", dto.getUserId());
        }

        return generated;
    }

    /**
     * Fetch user's email history as flat DTOs (no entity leaks, no N+1).
     */
    @Transactional(readOnly = true)
    public List<EmailHistoryResponse> getUserEmailHistory(Long userId) {
        // Verify user exists before querying history
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }

        return generatedEmailRepository.findByUserId(userId)
                .stream()
                .map(ge -> new EmailHistoryResponse(
                        ge.getId(),
                        ge.getEmailRequest().getCompany(),
                        ge.getEmailRequest().getPosition(),
                        ge.getSubject(),
                        ge.getBody(),
                        ge.getGeneratedAt()
                ))
                .toList();
    }
}