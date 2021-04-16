package com.win.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url="localhost:${server.port}/api/v1/lorem/", name="lorem")
public interface LoremClient {
	
	@RequestMapping("paragraphs?count=2&hasIntro=false")
	public String getDescription();
	

}
