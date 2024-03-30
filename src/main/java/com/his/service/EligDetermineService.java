package com.his.service;

import com.his.dto.ApplicationRegDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;

public interface EligDetermineService {
	
	boolean determineEligibility(SummaryDTO summary, ApplicationRegDTO application, PlanDTO plan);

}
