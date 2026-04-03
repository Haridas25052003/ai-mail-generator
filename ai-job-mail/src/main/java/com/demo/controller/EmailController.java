package com.demo.controller;

import com.demo.dto.EmailGenerateRequest;
import com.demo.dto.EmailGenerateResponse;
import com.demo.dto.EmailHistoryResponse;
import com.demo.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
@CrossOrigin
@RequiredArgsConstructor
public class EmailController {

	private final EmailService emailService;

	/**
	 * POST /api/email/generate
	 * Accepts JSON body (EmailGenerateRequest DTO).
	 * If userId is present, saves to history. Works for guests too.
	 */
	@PostMapping("/generate")
	public ResponseEntity<EmailGenerateResponse> generate(
			@Valid @RequestBody EmailGenerateRequest request) {

		EmailGenerateResponse response = emailService.generateAndSave(request);
		return ResponseEntity.ok(response);
	}

	/**
	 * GET /api/email/history/{userId}
	 * Returns flat DTOs — no entity leaks, no N+1.
	 */
	@GetMapping("/history/{userId}")
	public ResponseEntity<List<EmailHistoryResponse>> history(
			@PathVariable Long userId) {

		return ResponseEntity.ok(emailService.getUserEmailHistory(userId));
	}
}
