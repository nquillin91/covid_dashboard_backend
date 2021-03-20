package com.covid.dashboard.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class VaccineDataDTO {
	private PfizerVaccineDataResponse pfizerVaccineAllocations;
	private ModernaVaccineDataResponse modernaVaccineAllocations;
	private ArrayList<WeeklyVaccinationsData> weeklyVaccinations;
}