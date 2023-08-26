package com.restapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.entity.User;
import com.restapi.service.UserService;

@RestController
@RequestMapping("/notesapi")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("/signup")
	public User signup(@RequestBody User user) {
		return userservice.signup(user);
	}
	
	@GetMapping("/currentuser")
	public String getCurrentUser(Principal principal) {
		return principal.getName();
	}
}
