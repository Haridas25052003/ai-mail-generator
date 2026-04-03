package com.demo.repository;

import com.demo.model.GeneratedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeneratedEmailRepository extends JpaRepository<GeneratedEmail, Long> {

	// Explicit JPQL with JOIN FETCH avoids N+1 queries.
	// Without this, Spring would fire a separate SELECT for each EmailRequest
	// and another for each User when loading history.
	@Query("""
        SELECT ge FROM GeneratedEmail ge
        JOIN FETCH ge.emailRequest er
        JOIN FETCH er.user u
        WHERE u.id = :userId
        ORDER BY ge.generatedAt DESC
    """)
	List<GeneratedEmail> findByUserId(@Param("userId") Long userId);
}