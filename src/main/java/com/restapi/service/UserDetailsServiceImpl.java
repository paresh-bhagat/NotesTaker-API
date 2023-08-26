package com.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.restapi.repository.UserRepository;
import com.restapi.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private UserRepository userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userrepo.getUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return user;
	}

}
