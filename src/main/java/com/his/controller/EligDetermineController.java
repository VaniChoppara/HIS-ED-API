package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.client.ApplicationApiClient;
import com.his.client.DataCollectionApiClient;
import com.his.client.PlanApiClient;
import com.his.dto.ApplicationRegDTO;
import com.his.dto.EligDetermineDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;
import com.his.service.EligDetermineService;

@RestController
@RefreshScope
public class EligDetermineController {

	@Autowired
	DataCollectionApiClient dcClient;

	@Autowired
	PlanApiClient planClient;

	@Autowired
	ApplicationApiClient appClient;

	@Autowired
	EligDetermineService edService;

//	@Autowired
//	CoNoticeApiClient coClient;

	@PostMapping("/eligibility/{appNumber}")
	public ResponseEntity<EligDetermineDTO> determineEligibility(@PathVariable("appNumber") Integer appNumber) {

		ApplicationRegDTO application = appClient.getApplication(appNumber);
		PlanDTO plan = planClient.getPlan(application.getPlanId());
		SummaryDTO summary = dcClient.getSummary(appNumber);

		// Determine the Eligibility
		EligDetermineDTO edDto = edService.determineEligibility(summary, application, plan);

		// Make an entry into correspondence to print
//		CoNoticeDTO coDto = new CoNoticeDTO();
//		coDto.setAppNumber(appNumber);
//		coDto.setCoNoticeStatus("Pending");
//		coClient.generateCorrespondence(coDto);   		
	return new ResponseEntity<>(edDto, HttpStatus.OK);
	}

	@GetMapping("/eddetails")
	public ResponseEntity<List<EligDetermineDTO>> getAllEdDetalil() {
		List<EligDetermineDTO> edDetails = edService.getAl1EdDetalil();

		return new ResponseEntity<>(edDetails, HttpStatus.OK);
	}

	@GetMapping("/eddetails/{appNumber}")
	public ResponseEntity<EligDetermineDTO> getEdDetalilByAppNumber(@PathVariable("appNumber") Integer appNumber) {
		EligDetermineDTO edDetail = edService.getEdDetalilByAppNumber(appNumber);

		return new ResponseEntity<>(edDetail, HttpStatus.OK);
	}
	
	@GetMapping("/eddetails/plannames")
	public List<String> getPlanNames() {
		// TODO Auto-generated method stub
		return edService.getPlanNames();
	}


	@GetMapping("/eddetails/satuses")
	public List<String> getStatuses() {
		// TODO Auto-generated method stub
		return edService.getStatuses();
	}
	
	@PostMapping("/eddetails/search")
	public List<EligDetermineDTO> searchEdDetails(@RequestBody EligDetermineDTO edDto){
		return edService.searchEdDetails(edDto);
		
	}
}
