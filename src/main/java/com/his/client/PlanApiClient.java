package com.his.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.his.dto.PlanDTO;

@FeignClient(name="PLANS-API")
public interface PlanApiClient {
	@GetMapping("/plan/{id}")
	public PlanDTO getPlan(@PathVariable Integer id);
	
}
