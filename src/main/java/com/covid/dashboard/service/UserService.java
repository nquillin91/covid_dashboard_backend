package com.covid.dashboard.service.sql;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.covid.dashboard.component.UserEntityConverter;
import com.covid.dashboard.component.UserProfileConverter;
import com.covid.dashboard.dto.PasswordDTO;
import com.covid.dashboard.dto.SignUpDTO;
import com.covid.dashboard.dto.UserDTO;
import com.covid.dashboard.dto.UserProfileDTO;
import com.covid.dashboard.dto.UsernameDTO;
import com.covid.dashboard.exception.PasswordIncorrectException;
import com.covid.dashboard.model.nosql.ConfirmationTokenEntity;
import com.covid.dashboard.model.nosql.EmailAddressEntity;
import com.covid.dashboard.model.nosql.NameEntity;
import com.covid.dashboard.model.nosql.PhoneNumberEntity;
import com.covid.dashboard.model.nosql.ProvidedIncomeEntity;
import com.covid.dashboard.model.sql.AccountEntity;
import com.covid.dashboard.model.sql.BillingAddressEntity;
import com.covid.dashboard.model.sql.RoleEntity;
import com.covid.dashboard.model.sql.SsnEntity;
import com.covid.dashboard.model.sql.UserEntity;
import com.covid.dashboard.repository.nosql.EmailAddressRepository;
import com.covid.dashboard.repository.nosql.NameRepository;
import com.covid.dashboard.repository.nosql.PhoneNumberRepository;
import com.covid.dashboard.repository.nosql.ProvidedIncomeRepository;
import com.covid.dashboard.repository.sql.AccountRepository;
import com.covid.dashboard.repository.sql.BillingAddressRepository;
import com.covid.dashboard.repository.sql.RoleRepository;
import com.covid.dashboard.repository.sql.SSNRepository;
import com.covid.dashboard.repository.sql.UserRepository;
import com.covid.dashboard.service.nosql.ConfirmationTokenService;

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
    	Optional<UserEntity> optionalPrincipalUserEntity = userRepository.findByUsername(username);
    	
    	if (!optionalPrincipalUserEntity.isPresent()) {
    		throw new Exception("User not found with username: " + username);
    	}
    	
    	return userProfileConverter.toResponse(optionalPrincipalUserEntity.get());
    }

    public UserProfileDTO updateUserProfile(UserProfileDTO) throws Exception {
    }
    
    public ResponseEntity<?> processUserSignUp(SignUpReqest req) throws Exception {
    }
    
    private UserEntity parseSignUpRequest(SignUpReqest req) throws Exception {
    }
    
    private boolean doesUserExist(String username) {
    }
}