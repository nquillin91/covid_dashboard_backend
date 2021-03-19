package com.covid.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.dashboard.dto.SignUpRequest;
import com.covid.dashboard.dto.UserProfileDTO;
import com.covid.dashboard.service.UserService;

@RestController("UserController")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @GetMapping("/users/user/profile")
    public UserProfileDTO getUserProfile(HttpServletRequest request) throws Exception {
    	String username = request.getUserPrincipal().getName();
    	
        return userService.getUserProfile(username);
    }

    @PostMapping(value = "/users/user/profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserProfile(HttpServletRequest request, @RequestBody UserProfileDTO userProfileDTO) throws Exception {
    	String username = request.getUserPrincipal().getName();
    	
        userService.updateUserProfile(username, userProfileDTO);
    }
    
    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody SignUpRequest req) throws Exception {
		return userService.processUserSignUp(req);
	}
}