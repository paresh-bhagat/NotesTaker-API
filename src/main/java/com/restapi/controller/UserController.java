package com.restapi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.entity.User;
import com.restapi.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notesapi")
public class UserController {

	@Autowired
	private UserService userservice;
	
	// signup handler
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@Valid @RequestBody User newuser, BindingResult result) {

		// Check validation errors
		if (result.hasErrors()) {
			
			List<String> errorMessages = new ArrayList<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errorMessages.add(error.getDefaultMessage());
	        }
	        
	        return new ResponseEntity<>("Validation error: " + errorMessages, HttpStatus.BAD_REQUEST);
		}
	     
		if( newuser.getPassword().length()<1 || newuser.getPassword().length()>20 )
			return new ResponseEntity<>("Validation errors: Password between 1 to 20 characters" , HttpStatus.BAD_REQUEST);
		
		User user = new User();
		
		try {
			
			if(this.userservice.checkUsername(newuser.getUsername()))
				return new ResponseEntity<>("Username already exists", HttpStatus.NOT_IMPLEMENTED);
			
			user = this.userservice.signup(newuser);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
		
		return new ResponseEntity<>("Signup Successfull for "+ user.getUsername(), HttpStatus.CREATED);
	}
	
	// get user handler
	@GetMapping("/user/currentuser")
	public ResponseEntity<String> getCurrentUser(Principal principal) {
		return ResponseEntity.status(HttpStatus.OK).body(principal.getName());
	}
	
	// delete user handler
	@DeleteMapping("/user")
	public ResponseEntity<String> deleteUser(@Valid @RequestBody User user,BindingResult result,
			Principal principal) {
		
		// Check validation errors
		if (result.hasErrors()) {
					
			List<String> errorMessages = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errorMessages.add(error.getDefaultMessage());
			}
			        
			return new ResponseEntity<>("Validation errors: " + errorMessages, HttpStatus.BAD_REQUEST);
		}
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
			
		return new ResponseEntity<>("Account Deleted Successfully", HttpStatus.OK);
	}
	
	
}
