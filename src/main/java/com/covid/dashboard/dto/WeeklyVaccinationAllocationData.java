package com.covid.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeeklyVaccinationAllocationData {
	@JsonProperty("week_of_allocations")
	private String weekDate;
	
	@JsonProperty("_1st_dose_allocations")
	private String firstDoseAllocations;
	
	@JsonProperty("_2nd_dose_allocations")
	private String secondDoseAllocations;
}