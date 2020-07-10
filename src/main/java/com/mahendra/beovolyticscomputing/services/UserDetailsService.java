package com.mahendra.beovolyticscomputing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mahendra.beovolyticscomputing.entities.User;
import com.mahendra.beovolyticscomputing.repos.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found for username" + username);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
	}

}
