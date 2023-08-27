package com.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restapi.entity.User;
import com.restapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passswordEncoder;
	
	@Autowired
	private UserRepository userrepo;
	
	public List<User> getUsers(){
		return this.userrepo.findAll();
	}
	
	public User signup(User user) {
		user.setPassword(this.passswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return this.userrepo.save(user);
	}
	
	public User getUserDetails(String name) {
		return this.userrepo.getUserByUserName(name);
	}

	public boolean deleteUser(String name, String password) {
		
		User user = getUserDetails(name);
		
		if(passswordEncoder.matches(password, user.getPassword())==false)
			return false;
		
		this.userrepo.delete(user);
		
		return true;
	}
}
