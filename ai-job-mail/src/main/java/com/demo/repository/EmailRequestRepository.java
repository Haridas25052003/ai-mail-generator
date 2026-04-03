package com.demo.repository;

import com.demo.model.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRequestRepository extends JpaRepository<EmailRequest, Long> {
    // No changes needed — just ID type corrected to Long
}
