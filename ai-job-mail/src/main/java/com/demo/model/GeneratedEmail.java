package com.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "generated_emails")
@Getter
@Setter
@NoArgsConstructor
public class GeneratedEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subject;

	@Column(columnDefinition = "TEXT")
	private String body;

	@ManyToOne(fetch = FetchType.LAZY)   // LAZY: avoids loading full EmailRequest chain
	@JoinColumn(name = "email_request_id", nullable = false)
	private EmailRequest emailRequest;

	@Column(updatable = false)
	private LocalDateTime generatedAt;

	@PrePersist
	protected void onCreate() {
		this.generatedAt = LocalDateTime.now();
	}
}