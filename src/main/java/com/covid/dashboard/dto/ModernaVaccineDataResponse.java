package com.covid.dashboard.dto;

import java.util.List;

import lombok.Data;

@Data
public class ModernaVaccineDataResponse {
	private List<WeeklyVaccinationAllocationData> weeklyData;
}