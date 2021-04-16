package com.win.simple_people;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/simple_people")
public class SimplePeopleResources {
	
	@Autowired
	SimplePeopleService simplePeopleService;
	
	@GetMapping("/")
	public ResponseEntity<?> listSimplePeople(@RequestParam(required = false) Integer count, @RequestParam(required = false) String gender){
		if (count == null) count = 1;
		List<SimplePeople> simplePeople = simplePeopleService.getListSimplePeoples(count, gender);
		return ResponseEntity.ok(simplePeople);
	}

}
