package com.covid.dashboard.service;

import org.springframework.stereotype.Service;

import com.covid.dashboard.dto.CovidRestrictionsDTO;

@Service
public class CovidRestrictionsService {
	
	public CovidRestrictionsDTO getRestrictionData() {
		return new CovidRestrictionsDTO();
	}
}