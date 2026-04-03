package com.demo.repository;

import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {  // Long id

	Optional<User> findByEmail(String email);  // Optional > null — forces null-check at call site
}