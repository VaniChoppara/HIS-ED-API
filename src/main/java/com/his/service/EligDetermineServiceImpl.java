package com.his.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dto.ApplicationRegDTO;
import com.his.dto.KidDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;
import com.his.entity.EligDetermine;
import com.his.repository.EligDetermineRepository;

@Service
public class EligDetermineServiceImpl implements EligDetermineService {

	@Autowired
	EligDetermineRepository edRepository;

	@Override
	public boolean determineEligibility(SummaryDTO summary, ApplicationRegDTO application, PlanDTO plan) {
		EligDetermine edEntity = new EligDetermine();
		edEntity.setAppNumber(application.getAppNumber());
		boolean status = false;

		if (plan.getName().equalsIgnoreCase("SNAP") && summary.getIncomeDto().getSalIncome() <= 300) {
			status = true;
		} else if (plan.getName().equalsIgnoreCase("CCAP") && summary.getIncomeDto().getSalIncome() <= 300
				&& summary.getKidsDto().getKids().size() > 0 && checkAge(summary.getKidsDto().getKids())) {
			status = true;
		} else if (plan.getName().equalsIgnoreCase("Medicaid") && summary.getIncomeDto().getSalIncome() <= 300
				&& summary.getIncomeDto().getPropIncome() == 0) {
			status = true;
		} else if (plan.getName().equalsIgnoreCase("Medicare") && summary.getIncomeDto().getSalIncome() <= 300
				&& checkAge(application.getDob())) {
			status = true;
		} else if (plan.getName().equalsIgnoreCase("RIW") && summary.getIncomeDto().getSalIncome() <= 300
				&& summary.getEduDto().getHighestDegree().equals("Graduation")) {
			status = true;
		}

		if (status) {
			edEntity.setEligStatus("Approved");
			edEntity.setBenefitAmount(300);
			edEntity.setEligiDeterDate(LocalDate.now());
			edEntity.setEligStartdate(LocalDate.now());
			edEntity.setEligEndDate(LocalDate.now().plusMonths(6));
		} else {
			edEntity.setEligStatus("Denied");
			edEntity.setDenialReason("Did not meet the Eligibility Criteria");
			edEntity.setEligiDeterDate(LocalDate.now());
		}
		edRepository.save(edEntity);
		return status;
	}

	private boolean checkAge(List<KidDTO> kids) {
		for(KidDTO kidDto:kids) {
			LocalDate curDate= LocalDate.now();
			int years = Period.between(kidDto.getDob(), curDate).getYears();
			if(years<=16) {
				return true;
			}
		}		
		return false;
	}

	private boolean checkAge(LocalDate dob) {
		LocalDate curDate= LocalDate.now();
		int years = Period.between(dob, curDate).getYears();
		if(years>=65)
			return true;
		else 
			return false;
	}

	@Override
	public List<EligDetermine> getAl1EdDetalil() {
		return edRepository.findAll();
	}

	@Override
	public EligDetermine getEdDetalilByAppNumber(Integer appNumber) {
		
		return edRepository.findByAppNumber(appNumber);
	}

}
