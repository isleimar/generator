package com.win.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> listAll(){
		return categoryRepository.listAll();
	}
	
	public List<Category> randomSearch(Integer count){
		return categoryRepository.randomSearch(count);
	}
	

}
