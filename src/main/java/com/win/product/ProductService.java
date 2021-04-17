package com.win.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
			
	public List<Product> listAll(){
		return productRepository.listAll();
	}
	
	public List<Product> randomSearch(Integer count){
//		List<Product> products = productRepository.randomSearch(count);
//		products.stream().forEach(p -> p.setDescription(loremService.getLoremForParagraph(3, false)));
//		return products;
		return productRepository.randomSearch(count);
	}

}
