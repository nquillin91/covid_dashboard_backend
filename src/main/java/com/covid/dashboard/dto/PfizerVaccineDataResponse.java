package com.covid.dashboard.dto;

import java.util.List;

import lombok.Data;

@Data
public class PfizerVaccineDataResponse {
	private List<WeeklyVaccinationAllocationData> weeklyData;
}