package com.demo.service;

import com.demo.dto.AuthRequest;
import com.demo.dto.AuthResponse;
import com.demo.exception.DuplicateResourceException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor                       // constructor injection > @Autowired
public class UserService {

	private final UserRepository userRepository;

	/**
	 * Register: strictly fails if email already exists.
	 * This is different from login — registration must be explicit.
	 */
	@Transactional
	public AuthResponse register(AuthRequest request) {
		userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
			throw new DuplicateResourceException(
					"Email already registered: " + request.getEmail());
		});

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		User saved = userRepository.save(user);

		log.info("New user registered: {}", saved.getEmail());
		return toResponse(saved);
	}

	/**
	 * Login: returns existing user or creates one (social-login style, no password yet).
	 */
	@Transactional
	public AuthResponse login(AuthRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseGet(() -> {
					User newUser = new User();
					newUser.setName(request.getName());
					newUser.setEmail(request.getEmail());
					log.info("Auto-creating user on first login: {}", request.getEmail());
					return userRepository.save(newUser);
				});

		return toResponse(user);
	}

	@Transactional(readOnly = true)
	public AuthResponse getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return toResponse(user);
	}

	// ── Internal helper: entity → DTO ─────────────────────────────────────────
	// Centralized mapping means one place to change if AuthResponse fields change
	private AuthResponse toResponse(User user) {
		return new AuthResponse(user.getId(), user.getName(), user.getEmail());
	}
}