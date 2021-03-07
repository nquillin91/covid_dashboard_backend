package com.covid.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("MainController")
public class MainController {
    
    @GetMapping("/")
    public String checkStatus(HttpServletRequest request) throws Exception {
    	return "Backend is up and running.";
    }
}