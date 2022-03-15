package com.api.JWTRoleBasedAuth.model;

public class UserResponse {
	

	private final String token;

	public String getToken() {
		return token;
	}

	public UserResponse(String token) {
		super();
		this.token = token;
	}
}
