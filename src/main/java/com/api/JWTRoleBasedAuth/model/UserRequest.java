package com.api.JWTRoleBasedAuth.model;

public class UserRequest {
	
	private String username;
	private String password;
	private boolean isAdmin;
	private boolean isUser;
	private String token;
	
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isUser() {
		return isUser;
	}
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserRequest( String username,String token, boolean isUser, boolean isAdmin) {
		super();
		this.username=username;
		this.isAdmin = isAdmin;
		this.isUser = isUser;
		this.token = token;
	}
	
}
