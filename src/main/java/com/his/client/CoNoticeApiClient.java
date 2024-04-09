package com.his.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.his.dto.CoNoticeDTO;

@FeignClient(name="CO-API")
public interface CoNoticeApiClient {
	@PostMapping("/createCo")
	public String generateCorrespondence(@RequestBody CoNoticeDTO coNoticeDto);
	
}
