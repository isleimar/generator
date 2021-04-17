package com.win.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
	
	@Autowired
	ShopRepository shopRepository;
	
	public List<Shop> randomSearch(Integer count){
		return shopRepository.randomSearch(count);
	}
	
	
}
