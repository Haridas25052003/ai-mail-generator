package com.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_requests")
@Getter
@Setter
@NoArgsConstructor
public class EmailRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String position;

	@NotBlank
	private String company;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String jobDescription;

	@ManyToOne(fetch = FetchType.LAZY)   // LAZY: don't load User unless needed
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
}