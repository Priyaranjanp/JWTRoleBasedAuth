package com.api.JWTRoleBasedAuth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.JWTRoleBasedAuth.model.User;
import com.api.JWTRoleBasedAuth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
				new ArrayList<>());
	}

}
