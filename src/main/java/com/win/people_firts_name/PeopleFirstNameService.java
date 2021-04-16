package com.win.people_firts_name;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleFirstNameService {
	
	@Autowired
	private PeopleFirstNameRepository peopleFirstNameRepository;	
		
	public List<PeopleFirstName> listAll(){
		return peopleFirstNameRepository.findAll();
	}
	
	public List<PeopleFirstName> randomSearch(Integer count){		
		return peopleFirstNameRepository.randomSearch(count);
	}
	
	public List<PeopleFirstName> randomSearchGender(Integer count, String gender){		
		if(gender != null) {			
			return peopleFirstNameRepository.randomSearchGender(count, gender.toUpperCase());
		} else {
			return peopleFirstNameRepository.randomSearch(count);
		}
	}

}
