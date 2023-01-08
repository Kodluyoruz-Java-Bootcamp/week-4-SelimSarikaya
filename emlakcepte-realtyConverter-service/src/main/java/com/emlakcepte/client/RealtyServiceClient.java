package com.emlakcepte.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emlakcepte.model.Realty;
import com.emlakcepte.model.RealtyRequest;


@FeignClient(value = "emlakcepte-service")
public interface RealtyServiceClient {
	
	@PutMapping(value = "/realtyes/status")
	Realty realtyStatusUpdate(@RequestBody RealtyRequest realtyRequest);

}
