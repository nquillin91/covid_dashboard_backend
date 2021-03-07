package com.covid.dashboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.covid.dashboard.config.JwtUserDetails;
import com.covid.dashboard.model.sql.UserEntity;
import com.covid.dashboard.repository.sql.UserRepository;

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
		
		List<String> roles = new ArrayList<String>();
		for (RoleEntity role : user.getRoles()) {
			roles.add(role.getName().split("ROLE_")[1]);
		}
		
		return new JwtUserDetails(
				user.getUsername(), 
                user.getPassword(),
                user.getId(),
                new ArrayList<GrantedAuthority>()
        );
	}
	
	public UserDetails loadUserByUserId(String userId) {
		Optional<UserEntity> optionalUser = userRepository.findById(Long.parseLong(userId));

		if (!optionalUser.isPresent()) {
			throw new UsernameNotFoundException(userId + " not found.");
		}
		
		UserEntity user = optionalUser.get();
		
		List<String> roles = new ArrayList<String>();
		for (RoleEntity role : user.getRoles()) {
			roles.add(role.getName().split("ROLE_")[1]);
		}
		
		return new JwtUserDetails(
				user.getUsername(), 
                user.getPassword(),
                user.getId(),
                new ArrayList<GrantedAuthority>()
        );
	}
}