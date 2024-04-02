package com.his.service;

import java.util.List;

import com.his.dto.ApplicationRegDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;
import com.his.entity.EligDetermine;

public interface EligDetermineService {
	
	boolean determineEligibility(SummaryDTO summary, ApplicationRegDTO application, PlanDTO plan);

	List<EligDetermine> getAl1EdDetalil();

	EligDetermine getEdDetalilByAppNumber(Integer appNumber);

}
