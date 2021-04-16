package com.win.people_last_name;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/people_last_name")
public class PeopleLastNameResources {
	
	@Autowired
	PeopleLastNameService peopleLastNameService;

	@GetMapping("/")
	public ResponseEntity<?> listPeoplerLastName(){
		List<PeopleLastName> peopleLastNames = peopleLastNameService.listAll();
		return ResponseEntity.ok(peopleLastNames);
	}
	
	@GetMapping("/rnd")
	public ResponseEntity<?> randomSearch(@RequestParam(required = false) Integer count){
		List<PeopleLastName> peopleLastNames = peopleLastNameService.randomSearch(count);
		return ResponseEntity.ok(peopleLastNames);
	}

}
