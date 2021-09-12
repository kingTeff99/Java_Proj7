package com.nnk.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j
public class UsersService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

		User user = userRepo.findUserByUsername(username);
		
		if(user.equals(null)) {
			
			log.error("User not found in the DB");
			
			throw new UsernameNotFoundException("User not found in the DB");
			
		} else {
			
			log.info("User found in the DB : {}", username);
		}
		
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
	}
	
	public User saveUser(User users) {
		
		log.info("Saving new user {} to the DB", users.getUsername());
		
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		
		return userRepo.save(users);
		
	}
	
}
