package com.api.JWTRoleBasedAuth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.JWTRoleBasedAuth.model.Role;
import com.api.JWTRoleBasedAuth.model.User;
import com.api.JWTRoleBasedAuth.model.UserRequest;
import com.api.JWTRoleBasedAuth.model.UserResponse;
import com.api.JWTRoleBasedAuth.repository.RoleRepository;
import com.api.JWTRoleBasedAuth.repository.UserRepository;
import com.api.JWTRoleBasedAuth.service.UserServiceImpl;
import com.api.JWTRoleBasedAuth.util.JWTUtil;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JWTUtil jwtTokenUtil;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/addRole")
	public Role addRole(@RequestBody Role role) {
		return roleRepo.save(role);
	}
	
	@PostMapping("/registerUser")
	public User registerUser(@RequestBody User user) {
		String pwd = user.getPassword();
		String encodedPwd = bCryptPasswordEncoder.encode(pwd);
		user.setPassword(encodedPwd);
		System.out.println("User Saved");
		return userServiceImpl.save(user);
	}
	@GetMapping("/findAll")
	public List<User> findall() {
		return (List<User>) userRepo.findAll();
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequest userRequest) throws Exception {
		User user = userRepo.findByUsername(userRequest.getUsername());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (user.getUsername().equals(userRequest.getUsername())
				&& passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
			final UserDetails userDetails = userServiceImpl.loadUserByUsername(userRequest.getUsername());
			if(user.getRoles().getRoleId()==1) {
			final String token = jwtTokenUtil.generateToken(userDetails);
			userRequest.setUser(true);
			return ResponseEntity.ok(new UserRequest(userRequest.getUsername(),token,userRequest.isAdmin(),userRequest.isUser()));
			}
			else if (user.getRoles().getRoleId()==2){
				final String token = jwtTokenUtil.generateToken(userDetails);
				userRequest.setAdmin(true);
				System.out.println("JWT generated");
				return ResponseEntity.ok(new UserRequest(userRequest.getUsername(),token,userRequest.isAdmin(),userRequest.isUser()));
			}
			else {
				System.out.println("Not a valid User");
			}
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect username or password");
	}
	
	@GetMapping("/adminPage")
	public String getAdminDetails() {
		return "Welcome Admin...";
	}
	
	@GetMapping("/userPage")
	public String getUserDetails() {
		return "Welcome User...";
	}
	
}





