package com.covid.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WeeklyVaccinationsData {
	@JsonProperty("AdministeredCount")
	private String administeredCount;
	
	@JsonProperty("AdministeredCountChange")
	private String administeredCountChange;
	
	@JsonProperty("AdministeredCountRollAvg")
	private String administeredCountRollingAvg;
	
	@JsonProperty("PersonsFullyVaccinated")
	private String personsFullyVaccinated;
	
	@JsonProperty("Report_Date")
	private String reportDate;
	
	@JsonProperty("PctVaccinatedPopulation")
	private String pctVaccinatedPopulation;
}