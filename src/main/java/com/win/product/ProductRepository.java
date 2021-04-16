package com.win.product;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.win.services.CsvToClass;
import com.win.services.RandomUtil;

@Repository
public class ProductRepository {
		
	@Value("${data.file.product}")
	private String fileName;
	
	@Autowired
	private LoremClient loremClient;
	
	private List<Product> products;
	
	private void loadProducts() {
		if (products != null) return;
		products = new CsvToClass<Product>(fileName, ">") {
			@Override
			protected Product convert(HashMap<String, String> hash) {				
				return Product.builder()
						.name(hash.get("name"))	
						.description(null)
						.categories(List.of(hash.get("category").split("<")))
						.price(null)
						.build();
			}
		}.loadCsv();		
	}
			
	public List<Product> listAll(){		
		loadProducts();
		return setAttributList(products);
	}
	
	private List<Product> setAttributList(List<Product> _products){
		return _products.stream()
				.map(p -> getProduto(p))
				.collect(Collectors.toList());
	}
	
	private Product getProduto(Product product) {
		if (product == null) return null;
		if (product.getDescription() == null)
			product.setDescription(loremClient.getDescription());
		if (product.getPrice() == null)
			product.setPrice(RandomUtil.getDoubleRandom(0.01, 999.0));		
		return product;
	}
			
	public List<Product> randomSearch(Integer count){
		loadProducts();		
		return setAttributList(RandomUtil.getRandomList(products, count));	
	}

}
