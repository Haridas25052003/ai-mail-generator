package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public User login(
			@RequestParam String name,
			@RequestParam String email) {
		
		return userService.getOrCreateUser(name, email);
	}

}
