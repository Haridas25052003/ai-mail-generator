package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.GeneratedEmail;
import com.demo.service.EmailService;
import com.demo.service.GroqService;

@RestController
@RequestMapping("/api/email")
@CrossOrigin
public class EmailController {
	
	@Autowired
	private GroqService groqService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/generate")
	public String generateEmail(
			@RequestParam String company,
			@RequestParam String designation,
			@RequestParam String jobDescription) {
		
		String name="Candidate";
		
		return groqService.generateEmail(name, designation, company, jobDescription);
	}
	
	//for browser testing purpose
	@GetMapping("/generate")
	public String generateEmailByBrowser(
			@RequestParam String company,
			@RequestParam String designation,
			@RequestParam String jobDescription) {
		
		String name="Candidate";
		
		return groqService.generateEmail(name, designation, company, jobDescription);
	}
	
	@GetMapping("/history/{userId}")
	public List<GeneratedEmail> getEmailHistory(@PathVariable int userId){
		return emailService.getUserEmailHistory(userId);
	}

}
