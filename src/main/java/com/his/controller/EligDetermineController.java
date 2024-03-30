package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.his.client.ApplicationApiClient;
import com.his.client.DataCollectionApiClient;
import com.his.client.PlanApiClient;
import com.his.dto.ApplicationRegDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;
import com.his.service.EligDetermineService;

@RestController
public class EligDetermineController {
	
	@Autowired
	DataCollectionApiClient dcClient;
	
	@Autowired
	PlanApiClient planClient;
	
	@Autowired
	ApplicationApiClient appClient;
		
	@Autowired
	EligDetermineService edService;
	
	
	@GetMapping("/dcsummary/{appNumber}")
	public ResponseEntity<SummaryDTO> getSummaryFromDc(@PathVariable("appNumber") Integer appNumber){
		SummaryDTO summary = dcClient.getSummary(appNumber);
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}

	@GetMapping("/eligibility/{appNumber}")
	public ResponseEntity<String> determineEligibility(@PathVariable("appNumber") Integer appNumber){
		
		ApplicationRegDTO application = appClient.getApplication(appNumber);
		PlanDTO plan = planClient.getPlan(application.getPlanId());
		SummaryDTO summary = dcClient.getSummary(appNumber);
		
		
		boolean status=edService.determineEligibility(summary,application, plan);
		if(status)
			return new ResponseEntity<>("Approved", HttpStatus.OK);
		else
			return new ResponseEntity<>("Rejected", HttpStatus.OK);
			
	}
}
