package com.win.category;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.win.services.CsvToClass;
import com.win.services.RandomUtil;

@Repository
public class CategoryRepository {
	
	@Value("${data.file.category}")
	private String fileName;
	
	private List<Category> categories;
	
	private void loadCategories() {
		if (categories != null) return;
		categories = new CsvToClass<Category>(fileName,",") {
			@Override
			protected Category convert(HashMap<String, String> hash) {				
				return Category.builder()
						.name(hash.get("category"))
						.build();
			}
		}.loadCsv();
	}
	
	public List<Category> listAll(){
		loadCategories();
		return categories;
	}
	
	public List<Category> randomSearch(Integer count){
		loadCategories();
		return RandomUtil.getRandomList(categories, count);
	}
	
}
