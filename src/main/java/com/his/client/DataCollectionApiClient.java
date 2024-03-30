package com.his.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.his.dto.SummaryDTO;


@FeignClient(name="DC-API")
public interface DataCollectionApiClient {
	
	@GetMapping("/summary/{appNumber}")
	public SummaryDTO getSummary(@PathVariable("appNumber") Integer appNumber);

}
