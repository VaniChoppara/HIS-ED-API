package com.his.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.EligDetermine;

public interface EligDetermineRepository extends JpaRepository<EligDetermine, Integer>{

	EligDetermine findByAppNumber(Integer appNumber);

}
