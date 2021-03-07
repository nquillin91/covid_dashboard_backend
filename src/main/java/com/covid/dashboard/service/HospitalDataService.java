package com.covid.dashboard.service;

import org.springframework.stereotype.Service;

import com.covid.dashboard.dto.HospitalDataDTO;

@Service
public class HospitalDataService {
	
	public HospitalDataDTO getHospitalData() {
		return new HospitalDataDTO();
	}
}