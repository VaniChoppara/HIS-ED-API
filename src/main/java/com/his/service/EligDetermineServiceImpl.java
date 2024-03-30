package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dto.ApplicationRegDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;
import com.his.entity.EligDetermine;
import com.his.repository.EligDetermineRepository;

@Service
public class EligDetermineServiceImpl implements EligDetermineService{

	@Autowired
	EligDetermineRepository edRepository;
	
	@Override
	public boolean determineEligibility(SummaryDTO summary, ApplicationRegDTO application, PlanDTO plan) {
		EligDetermine edEntity= new EligDetermine();
		edEntity.setAppNumber(application.getAppNumber());
		
		if(plan.getName().equalsIgnoreCase("SNAP") && summary.getIncomeDto().getSalIncome()<=350){
			edEntity.setEligStatus("Approved");
			edEntity.setBenefitAmount(300);			
			return true;
		}else if(plan.getName().equalsIgnoreCase("CCAP") && summary.getIncomeDto().getSalIncome()<=350 
				&& summary.getKidsDto().getKids().size()>0 ) {
			edEntity.setEligStatus("Approved");
			edEntity.setBenefitAmount(300);			
			return true;
		}
		edEntity.setEligStatus("Denied");
		edEntity.setDenialReason("Did not meet the Eligibility Criteria");
		edRepository.save(edEntity);
		return false;
	}

}
