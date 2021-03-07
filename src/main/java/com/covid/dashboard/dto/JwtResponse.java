package com.covid.dashboard.dto;

import lombok.Getter;

public class JwtResponse {
	@Getter
	private final String jwtToken;
	
	public JwtResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}