package com.win.people_firts_name;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/people_name")
public class PeopleFirstNameResources {
	
	@Autowired
	PeopleFirstNameService peopleFirstNameService;
	
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public void addPeople (@RequestBody PeopleFirstName peopleName) {
		peopleFirstNameService.addPeople(peopleName);		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> listPeopleName(){
		List<PeopleFirstName> peopleNames = peopleFirstNameService.listAll();
		return ResponseEntity.ok(peopleNames);		
	}
	
	@GetMapping("/rnd")
	public ResponseEntity<?> randomSearch(@RequestParam(required = false) Integer count, @RequestParam(required = false) String gender){		
		return ResponseEntity.ok(peopleFirstNameService.randomSearchGender(count, gender));
	}
}
