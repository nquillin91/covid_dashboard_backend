package com.covid.dashboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.covid.dashboard.dto.ModernaVaccineDataResponse;
import com.covid.dashboard.dto.PfizerVaccineDataResponse;
import com.covid.dashboard.dto.VaccineDataDTO;
import com.covid.dashboard.dto.WeeklyVaccinationAllocationData;
import com.covid.dashboard.dto.WeeklyVaccinationsData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class VaccineDataService {
	@Value("${app.cdc.data.app.token}")
	private String token;
	
	public VaccineDataDTO getVaccineData() {
		VaccineDataDTO data = new VaccineDataDTO();
		
		try {
			PfizerVaccineDataResponse pfizerData = getPfizerData();
			ModernaVaccineDataResponse modernaData = getModernaData();
			ArrayList<WeeklyVaccinationsData> weeklyVaccinations = getVaccinationsData();
			
			data.setModernaVaccineAllocations(modernaData);
			data.setPfizerVaccineAllocations(pfizerData);
			data.setWeeklyVaccinations(weeklyVaccinations);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	private PfizerVaccineDataResponse getPfizerData() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request
				.Builder()
				.header("X-App-Token", this.token)
				.url("https://data.cdc.gov/resource/saz5-9hgg.json?jurisdiction=Illinois")
				.build();
		
		Response pfizerResponse = client.newCall(request).execute();
		
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                                            List.class, WeeklyVaccinationAllocationData.class);
        ArrayList<WeeklyVaccinationAllocationData> weeklyData = objectMapper.readValue(pfizerResponse.body().string(), collectionType);
		pfizerResponse.body().close();
		
		PfizerVaccineDataResponse pfizerVaccineData = new PfizerVaccineDataResponse();
		pfizerVaccineData.setWeeklyData(weeklyData);
		
		return pfizerVaccineData;
	}
	
	private ModernaVaccineDataResponse getModernaData() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request
				.Builder()
				.header("X-App-Token", this.token)
				.url("https://data.cdc.gov/resource/b7pe-5nws.json?jurisdiction=Illinois")
				.build();
		
		Response modernaResponse = client.newCall(request).execute();
		
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                                            List.class, WeeklyVaccinationAllocationData.class);
        ArrayList<WeeklyVaccinationAllocationData> weeklyData = objectMapper.readValue(modernaResponse.body().string(), collectionType);
        modernaResponse.body().close();
		
		ModernaVaccineDataResponse modernaVaccineData = new ModernaVaccineDataResponse();
		modernaVaccineData.setWeeklyData(weeklyData);
		
		return modernaVaccineData;
	}
	
	private ArrayList<WeeklyVaccinationsData> getVaccinationsData() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request
				.Builder()
				.url("https://idph.illinois.gov/DPHPublicInformation/api/COVIDExport/GetVaccineAdministration?countyName=Cook")
				.build();
		
		Response weeklyVaccinationsResponse = client.newCall(request).execute();
		
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                                            List.class, WeeklyVaccinationsData.class);
        ArrayList<WeeklyVaccinationsData> weeklyVaccinations = objectMapper.readValue(weeklyVaccinationsResponse.body().string(), collectionType);
        weeklyVaccinationsResponse.body().close();
		
		return weeklyVaccinations;
	}
}