package com.covid.dashboard.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.covid.dashboard.config.JwtUserDetails;
import com.covid.dashboard.model.UserEntity;
import com.covid.dashboard.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

		if (!optionalUser.isPresent()) {
			throw new UsernameNotFoundException(username + " not found.");
		}
		
		UserEntity user = optionalUser.get();
		
		return new JwtUserDetails(
				user.getUsername(), 
                user.getPassword(),
                new ArrayList<GrantedAuthority>()
        );
	}
}