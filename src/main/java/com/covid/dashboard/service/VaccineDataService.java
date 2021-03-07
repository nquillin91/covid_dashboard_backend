package com.covid.dashboard.service;

import org.springframework.stereotype.Service;

import com.covid.dashboard.dto.VaccineDataDTO;

@Service
public class VaccineDataService {
	
	public VaccineDataDTO getVaccineData() {
		return new VaccineDataDTO();
	}
}