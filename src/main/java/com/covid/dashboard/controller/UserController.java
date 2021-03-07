package com.covid.dashboard.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.dashboard.config.JwtUserDetails;
import com.covid.dashboard.dto.BillingAddressDTO;
import com.covid.dashboard.dto.EmailAddressDTO;
import com.covid.dashboard.dto.PasswordDTO;
import com.covid.dashboard.dto.PhoneNumberDTO;
import com.covid.dashboard.dto.ProvidedIncomeDTO;
import com.covid.dashboard.dto.SignUpDTO;
import com.covid.dashboard.dto.UserDTO;
import com.covid.dashboard.dto.UserProfileDTO;
import com.covid.dashboard.dto.UsernameDTO;
import com.covid.dashboard.model.nosql.ConfirmationTokenEntity;
import com.covid.dashboard.service.nosql.ConfirmationTokenService;
import com.covid.dashboard.service.nosql.EmailAddressService;
import com.covid.dashboard.service.nosql.PhoneNumberService;
import com.covid.dashboard.service.nosql.ProvidedIncomeService;
import com.covid.dashboard.service.sql.BillingAddressService;
import com.covid.dashboard.service.sql.UserService;

@RestController("UserController")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @GetMapping("/users/user/profile")
    public UserProfileDTO getUserProfile(HttpServletRequest request) throws Exception {
    	String username = request.getUserPrincipal().getName();
    	
        return userService.getUserProfile(username);
    }

    @PostMapping("/users/user/profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO) throws Exception {
        String username = request.getUserPrincipal().getName();

        return userService.updateUserProfile(userProfileDTO);
    }
    
    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO signUp(@RequestBody SignUpDTO signUpDto) throws Exception {
		UserDTO userData = new UserDTO();
		userData = userService.signUpUser(signUpDto);
		
		return userData;
	}
}