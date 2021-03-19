package com.covid.dashboard.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.covid.dashboard.component.UserProfileConverter;
import com.covid.dashboard.dto.SignUpRequest;
import com.covid.dashboard.dto.UserProfileDTO;
import com.covid.dashboard.model.UserEntity;
import com.covid.dashboard.repository.UserRepository;

@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserProfileConverter userProfileConverter;

    @Autowired
    private JwtAuthenticationService authService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserProfileDTO getUserProfile(String username) throws Exception {
    	Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
    	
    	if (!optionalUser.isPresent()) {
    		throw new Exception("User not found.");
    	}
    	
    	return userProfileConverter.toResponse(optionalUser.get());
    }

    public void updateUserProfile(String username, UserProfileDTO userProfileDTO) throws Exception {
    	Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
		
		if (optionalUser.isPresent()) {
			UserEntity user = optionalUser.get();
			
			user.setFirstName(userProfileDTO.getFirstName());
			user.setMiddleName(userProfileDTO.getMiddleName());
			user.setLastName(userProfileDTO.getLastName());
			user.setGender(userProfileDTO.getGender());
			user.setPhoneNumber(userProfileDTO.getPhoneNumber());
			user.setZipcode(userProfileDTO.getZipcode());
			user.setHasPreExistingConditions(userProfileDTO.isHasPreExistingConditions());
			user.setFollowingHygieneGuidelines(userProfileDTO.isFollowingHygieneGuidelines());
			user.setAdheringToPPPGuidelines(userProfileDTO.isAdheringToPPPGuidelines());
			user.setVaccineStatus(userProfileDTO.getVaccineStatus());
			user.setHasRoommates(userProfileDTO.isHasRoommates());
			user.setDirectExposureCount(userProfileDTO.getDirectlyExposedCount());
			user.setIndirectExposureCount(userProfileDTO.getIndirectlyExposedCount());
			user.setFirstResponder(userProfileDTO.isFirstResponder());
			user.setEssentialWorker(userProfileDTO.isEssentialWorker());
			
			userRepository.save(user);
		}
    }
    
    public ResponseEntity<?> processUserSignUp(SignUpRequest req) throws Exception {
    	parseSignUpRequest(req);
    	
    	return authService.createAuthenticationToken(req.getUsername(), req.getPassword());
    }
    
    private void parseSignUpRequest(SignUpRequest req) throws Exception {
    	if (doesUserExist(req.getUsername())) {
    		throw new Exception(MessageFormat.format("Username - {0} - already exists.", req.getUsername()));
    	}
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	LocalDate birthDate = LocalDate.parse(req.getBirthDate(), formatter);
    	
    	UserEntity user = new UserEntity(req.getUsername(), req.getPassword(), birthDate);
    	String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    	user.setPassword(encryptedPassword);
    	
		user.setFirstName(req.getFirstName());
		user.setMiddleName(req.getMiddleName());
		user.setLastName(req.getLastName());
		user.setGender(req.getGender());
		user.setPhoneNumber(req.getPhoneNumber());
		user.setZipcode(req.getZipcode());
		user.setHasPreExistingConditions(req.isHasPreExistingConditions());
		user.setFollowingHygieneGuidelines(req.isFollowingHygieneGuidelines());
		user.setAdheringToPPPGuidelines(req.isAdheringToPPPGuidelines());
		user.setVaccineStatus(req.getVaccineStatus());
		user.setHasRoommates(req.isHasRoommates());
		user.setDirectExposureCount(req.getDirectlyExposedCount());
		user.setIndirectExposureCount(req.getIndirectlyExposedCount());
		user.setFirstResponder(req.isFirstResponder());
		user.setEssentialWorker(req.isEssentialWorker());
    	
    	userRepository.save(user);
    }
    
    private boolean doesUserExist(String username) {
    	Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
    	
    	if (optionalUser.isPresent()) {
    		return true;
    	}
    	
    	return false;
    }
}