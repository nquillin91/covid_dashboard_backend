package com.covid.dashboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.covid.dashboard.dto.SignUpRequest;
import com.covid.dashboard.dto.UserProfileDTO;
import com.covid.dashboard.repository.UserRepository;

@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Value("${app.host}")
	private String appHost;
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtAuthenticationService authService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserProfileDTO getUserProfile(Long userId) throws Exception {
    	return new UserProfileDTO();
    }

    public UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO) throws Exception {
    	return userProfileDTO;
    }
    
    public ResponseEntity<?> processUserSignUp(SignUpRequest req) throws Exception {
    	return ResponseEntity.ok("Mock");
    }
    
    // private UserEntity
    private void parseSignUpRequest(SignUpRequest req) throws Exception {
    }
    
    private boolean doesUserExist(String username) {
    	return true;
    }
}