package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserPrinciple;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepo;



@Service
public class MyUserDetailService implements UserDetailsService {
	
	
	@Autowired
	private UserRepo repo;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByusername(username);
		if(user == null) {
			System.out.println("User 404");
			throw new UsernameNotFoundException("user 404");
		}
		return new UserPrinciple(user);
	}
	
	//taking input from the user and encoding the password to bcrypt to keep it secure and encryption is then by the securityconfig file.
	public Users adduser(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		return repo.save(user);
		
	}

}
