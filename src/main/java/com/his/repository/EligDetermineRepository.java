package com.his.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.his.entity.EligDetermine;

public interface EligDetermineRepository extends JpaRepository<EligDetermine, Integer>{

	EligDetermine findByAppNumber(Integer appNumber);
	
	@Query("select distinct(planName) from EligDetermine")
	public List<String> getPlanNames();
	@Query("select distinct(eligStatus) from EligDetermine")
	public List<String> getPlanStatuses();

}
