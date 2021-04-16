package com.win.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
public class CategoryResources {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public ResponseEntity<?> listCategory(){
		return ResponseEntity.ok(categoryService.listAll());
	}
	
	@GetMapping("/rnd")
	public ResponseEntity<?> randomSearch(@RequestParam(required = false) Integer count){
		if(count == null) count = 1;
		return ResponseEntity.ok(categoryService.randomSearch(count));
	}

}
