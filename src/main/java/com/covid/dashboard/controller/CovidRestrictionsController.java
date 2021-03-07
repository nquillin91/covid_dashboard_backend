package com.covid.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid.dashboard.dto.CovidRestrictionsDTO;
import com.covid.dashboard.service.CovidRestrictionsService;

@RestController("CovidRestrictionsController")
public class CovidRestrictionsController {
	
    @Autowired
    private CovidRestrictionsService covidRestrictionsService;
    
    @GetMapping("/restrictions")
    public CovidRestrictionsDTO getCovidRestrictions() throws Exception {
        return covidRestrictionsService.getRestrictionData();
    }
}