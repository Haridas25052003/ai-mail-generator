package com.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GeneratedEmail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String subject;
	@Column(columnDefinition="TEXT")
	private String body;
	@ManyToOne
	private EmailRequest emailRequest;
	@Override
	public String toString() {
		return "GeneratedEmail [id=" + id + ", subject=" + subject + ", body=" + body + ", emailRequest=" + emailRequest
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public EmailRequest getEmailRequest() {
		return emailRequest;
	}
	public void setEmailRequest(EmailRequest emailRequest) {
		this.emailRequest = emailRequest;
	}
	
	

}
