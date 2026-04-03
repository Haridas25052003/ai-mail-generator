package com.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;                          // Long > int for scalability

	@NotBlank
	private String name;

	@Email
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;

	// password field kept but excluded from toString for security
	@Column(nullable = true)
	private String password;

	@Column(updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User[id=" + id + ", name=" + name + ", email=" + email + "]";
		// password intentionally excluded
	}
}