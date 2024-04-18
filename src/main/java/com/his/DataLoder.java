package com.his;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.his.entity.EligDetermine;
import com.his.repository.EligDetermineRepository;

@Component
public class DataLoder implements ApplicationRunner {

	@Autowired
	EligDetermineRepository elgRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

//		for (int i = 0; i < 5000; i++) {
//			EligDetermine eligEntity = new EligDetermine();
//			eligEntity.setPlanName("SNAP");
//			eligEntity.setEligStatus("Approved");
//			eligEntity.setEligStartdate(LocalDate.now());
//			eligEntity.setEligEndDate(LocalDate.now().plusDays(15));
//			eligEntity.setBenefitAmount(300);
//			eligEntity.setAppNumber(9);
//			elgRepo.save(eligEntity);
//		}

	}

}
