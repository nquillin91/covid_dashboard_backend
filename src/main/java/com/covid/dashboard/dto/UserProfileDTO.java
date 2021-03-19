package com.covid.dashboard.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
	private String username;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String birthDate;
	private String phoneNumber;
	private String zipcode;
	private boolean hasPreExistingConditions;
	private boolean isFollowingHygieneGuidelines;
	private boolean isAdheringToPPPGuidelines;
	private String vaccineStatus;
	private boolean hasRoommates;
	private int directlyExposedCount;
	private int indirectlyExposedCount;
	private boolean isFirstResponder;
	private boolean isEssentialWorker;
	
	public UserProfileDTO() {
		System.out.println("UserProfileDTO Created");
	}
}