package com.his.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dto.ApplicationRegDTO;
import com.his.dto.EligDetermineDTO;
import com.his.dto.KidDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;
import com.his.entity.EligDetermine;
import com.his.exception.EdException;
import com.his.repository.EligDetermineRepository;

@Service
public class EligDetermineServiceImpl implements EligDetermineService {

	@Autowired
	EligDetermineRepository edRepository;

	@Override
	public EligDetermineDTO determineEligibility(SummaryDTO summary, ApplicationRegDTO application, PlanDTO plan) {
		EligDetermine edEntity = new EligDetermine();
		EligDetermineDTO eligDto = new EligDetermineDTO();
		EligDetermine edByAppNumber = edRepository.findByAppNumber(application.getAppNumber());
		if (edByAppNumber != null) {
			// throw new EdException("Application proceessed already. Please proceed to view
			// the status");
			BeanUtils.copyProperties(edByAppNumber, eligDto);
			return eligDto;
		}
		edEntity.setAppNumber(application.getAppNumber());
		edEntity.setPlanName(plan.getName());
		edEntity.setEligiDeterDate(LocalDate.now());

		if (plan.getName().equalsIgnoreCase("SNAP")) {
			if (summary.getIncomeDto().getSalIncome() <= 300) {
				edEntity.setEligStatus("Approved");
				edEntity.setBenefitAmount(300);
				edEntity.setEligStartdate(LocalDate.now());
				edEntity.setEligEndDate(LocalDate.now().plusMonths(6));
			} else {
				edEntity.setDenialReason("High Income");
				edEntity.setEligStatus("Approved");
			}
		}else if (plan.getName().equalsIgnoreCase("CCAP")) {
			if (summary.getIncomeDto().getSalIncome() <= 300) {
				if (summary.getKidsDto().getKids().size() > 0) {
					if (checkAge(summary.getKidsDto().getKids())) {
						edEntity.setEligStatus("Approved");
						edEntity.setBenefitAmount(300);
						edEntity.setEligStartdate(LocalDate.now());
						edEntity.setEligEndDate(LocalDate.now().plusMonths(6));
					} else {
						edEntity.setDenialReason("Kid age is above 16");
						edEntity.setEligStatus("Denied");
					}
				} else {
					edEntity.setDenialReason("No Kids available");
					edEntity.setEligStatus("Denied");
				}
			} else {
				edEntity.setDenialReason("High Income");
				edEntity.setEligStatus("Denied");
			}
		}else if (plan.getName().equalsIgnoreCase("Medicaid")) {
			if (summary.getIncomeDto().getSalIncome() <= 300 && summary.getIncomeDto().getPropIncome() == 0) {
				edEntity.setEligStatus("Approved");
				edEntity.setBenefitAmount(300);
				edEntity.setEligStartdate(LocalDate.now());
				edEntity.setEligEndDate(LocalDate.now().plusMonths(6));
			} else {
				edEntity.setDenialReason("High Income");
				edEntity.setEligStatus("Denied");
			}
		}else if (plan.getName().equalsIgnoreCase("Medicare")) {
			if (summary.getIncomeDto().getSalIncome() <= 300) {
				if (checkAge(application.getDob())) {
					edEntity.setEligStatus("Approved");
					edEntity.setBenefitAmount(300);
					edEntity.setEligStartdate(LocalDate.now());
					edEntity.setEligEndDate(LocalDate.now().plusMonths(6));
				} else {
					edEntity.setDenialReason("Age not matched");
					edEntity.setEligStatus("Denied");
				}
			} else {
				edEntity.setDenialReason("High Income");
				edEntity.setEligStatus("Denied");
			}
		}else if (plan.getName().equalsIgnoreCase("RIW")) {
			if (summary.getIncomeDto().getSalIncome() == 0) {
				if (summary.getEduDto().getHighestDegree() != null) {
					edEntity.setEligStatus("Approved");
					edEntity.setBenefitAmount(300);
					edEntity.setEligStartdate(LocalDate.now());
					edEntity.setEligEndDate(LocalDate.now().plusMonths(6));
				} else {
					edEntity.setDenialReason("No Graduation");
					edEntity.setEligStatus("Denied");
				}
			}else {
				edEntity.setDenialReason("Have Income");
				edEntity.setEligStatus("Denied");
			}
		}
		EligDetermine eligDetermine = edRepository.save(edEntity);

		BeanUtils.copyProperties(eligDetermine, eligDto);
		return eligDto;
	}

	

	private boolean checkAge(List<KidDTO> kids) {
		for (KidDTO kidDto : kids) {
			LocalDate curDate = LocalDate.now();
			int years = Period.between(kidDto.getDob(), curDate).getYears();
			if (years <= 16) {
				return true;
			}
		}
		return false;
	}

	private boolean checkAge(LocalDate dob) {
		LocalDate curDate = LocalDate.now();
		int years = Period.between(dob, curDate).getYears();
		if (years >= 65)
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
