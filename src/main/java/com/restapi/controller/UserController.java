package com.restapi.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// signup handler
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody User newuser) {
		
		User user = new User();
		
		try {
			user = this.userservice.signup(newuser);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	// get user handler
	@GetMapping("/user/currentuser")
	public ResponseEntity<String> getCurrentUser(Principal principal) {
		return ResponseEntity.status(HttpStatus.OK).body(principal.getName());
	}
	
	// delete user handler
	@DeleteMapping("/user")
	public ResponseEntity<Void> deleteUser(@RequestBody User user,Principal principal) {
		
		String name = principal.getName();
		
		if(name.equals(user.getUsername())==false)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		try {
			if( this.userservice.deleteUser(name,user.getPassword())==false )
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
			
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
}
