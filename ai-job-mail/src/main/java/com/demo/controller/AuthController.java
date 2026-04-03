package com.demo.controller;

import com.demo.dto.AuthRequest;
import com.demo.dto.AuthResponse;
import com.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) {
		// @RequestBody JSON > @RequestParam  (better for frontend fetch/axios)
		// @Valid triggers bean validation (@NotBlank, @Email) before hitting service
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		return ResponseEntity.ok(userService.login(request));
	}

	// Admin: get user by ID
	@GetMapping("/users/{id}")
	public ResponseEntity<AuthResponse> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
}
