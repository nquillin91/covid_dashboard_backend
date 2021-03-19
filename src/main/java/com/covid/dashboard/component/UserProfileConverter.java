package com.covid.dashboard.component;


import org.springframework.stereotype.Component;

import com.covid.dashboard.dto.UserProfileDTO;
import com.covid.dashboard.model.UserEntity;

@Component
public class UserProfileConverter {
	
	public UserProfileDTO toResponse(UserEntity userEntity) {
		UserProfileDTO userProfile = new UserProfileDTO();
		
		// Set username on the profile
		userProfile.setUsername(userEntity.getUsername());
		
		userProfile.setFirstName(userEntity.getFirstName());
		userProfile.setMiddleName(userEntity.getMiddleName());
		userProfile.setLastName(userEntity.getLastName());
		
		// Pull birthdate and then format it to a string for the profile
		if (userEntity.getBirthdate() != null) {
			userProfile.setBirthDate(userEntity.getBirthdate().toString());	
		}
		
		userProfile.setGender(userEntity.getGender());
		userProfile.setPhoneNumber(userEntity.getPhoneNumber());
		userProfile.setZipcode(userEntity.getZipcode());
		userProfile.setHasPreExistingConditions(userEntity.isHasPreExistingConditions());
		userProfile.setFollowingHygieneGuidelines(userEntity.isFollowingHygieneGuidelines());
		userProfile.setAdheringToPPPGuidelines(userEntity.isAdheringToPPPGuidelines());
		userProfile.setVaccineStatus(userEntity.getVaccineStatus());
		userProfile.setHasRoommates(userEntity.isHasRoommates());
		userProfile.setDirectlyExposedCount(userEntity.getDirectExposureCount());
		userProfile.setIndirectlyExposedCount(userEntity.getIndirectExposureCount());
		userProfile.setFirstResponder(userEntity.isFirstResponder());
		userProfile.setEssentialWorker(userEntity.isEssentialWorker());
		
		return userProfile;
    }
}