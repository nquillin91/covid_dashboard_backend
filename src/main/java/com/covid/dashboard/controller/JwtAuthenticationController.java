package com.covid.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.covid.dashboard.config.JwtTokenUtil;
import com.covid.dashboard.model.JwtRequest;
import com.covid.dashboard.model.JwtResponse;
import com.covid.dashboard.service.JwtAuthenticationService;

@RestController("JwtAuthenticationController")
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private JwtAuthenticationService authService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
		return authService.createAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
	}
}