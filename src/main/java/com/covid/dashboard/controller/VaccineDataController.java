package com.covid.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid.dashboard.dto.VaccineDataDTO;
import com.covid.dashboard.service.VaccineDataService;

@RestController("VaccineDataController")
public class VaccineDataController {
    
    @Autowired
    private VaccineDataService vaccineDataService;
    
    @GetMapping("/vaccine-data")
    public VaccineDataDTO getVaccineData() throws Exception {
        return vaccineDataService.getVaccineData();
    }
}