package com.covid.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid.dashboard.dto.HospitalDataDTO;
import com.covid.dashboard.service.HospitalDataService;

@RestController("HospitalDataController")
public class HospitalDataController {
    
    @Autowired
    private HospitalDataService hospitalDataService;
    
    @GetMapping("/hospital-data")
    public HospitalDataDTO getHospitalData() throws Exception {
        return hospitalDataService.getHospitalData();
    }
}