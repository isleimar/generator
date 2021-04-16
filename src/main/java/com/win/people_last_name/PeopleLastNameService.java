package com.win.people_last_name;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleLastNameService {
	
	@Autowired
	private PeopleLastNameRepository peopleLastNameRepository;
		
	public List<PeopleLastName> listAll(){
		return peopleLastNameRepository.findAll();
	}
	
	public List<PeopleLastName> randomSearch(Integer count){
		return peopleLastNameRepository.randomSearch(count);
	}

}
