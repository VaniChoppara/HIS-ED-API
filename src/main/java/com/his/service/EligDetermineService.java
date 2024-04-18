package com.his.service;

import java.util.List;

import com.his.dto.ApplicationRegDTO;
import com.his.dto.EligDetermineDTO;
import com.his.dto.PlanDTO;
import com.his.dto.SummaryDTO;

public interface EligDetermineService {

	EligDetermineDTO determineEligibility(SummaryDTO summary, ApplicationRegDTO application, PlanDTO plan);

	List<EligDetermineDTO> getAl1EdDetalil();

	EligDetermineDTO getEdDetalilByAppNumber(Integer appNumber);

	public List<String> getPlanNames();

	public List<String> getStatuses();

	List<EligDetermineDTO> searchEdDetails(EligDetermineDTO edDto);

}
