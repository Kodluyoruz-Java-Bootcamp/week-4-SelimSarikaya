package com.emlakcepte.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("products")
public interface ProductServiceClient {

	@PostMapping(value = "/products")
	Product create(@RequestBody ProductRequest productRequest);
}
