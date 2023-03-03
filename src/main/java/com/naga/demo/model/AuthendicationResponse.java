package com.naga.demo.model;

public class AuthendicationResponse {
	private final String jwt;

	public AuthendicationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
}
