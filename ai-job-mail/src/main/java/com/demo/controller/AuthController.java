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

import com.demo.model.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	//register first
	@PostMapping("/register")
	public User register(
			@RequestParam String name,
			@RequestParam String email) {
		return userService.register(name, email);
	}
	
	
	//login
	@PostMapping("/login")
	public User login(
			@RequestParam String name,
			@RequestParam String email) {
		
		return userService.getOrCreateUser(name, email);
	}
	
	//optional if admin page created for getting all user in dashboard
	@GetMapping("/req1/{id}")
	public User m1(@PathVariable int id){
		return userService.getUserById(id);
	}

}
