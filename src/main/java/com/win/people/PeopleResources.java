package com.win.people;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/people")
public class PeopleResources {
	
	
	@Autowired
	PeopleService peopleService;
	
	
	@GetMapping("")
	public ResponseEntity<?> randomSearch(@RequestParam(required = false) Integer count, @RequestParam(required = false) String gender){
		List<People> people= peopleService.getPeoples(count, gender);
		return ResponseEntity.ok(people);
	}
}
