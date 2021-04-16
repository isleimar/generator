package com.win.people_firts_name;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.win.people.Gender;
import com.win.services.CsvToClass;
import com.win.services.MyUtil;
import com.win.services.RandomUtil;

@Repository
public class PeopleFirstNameRepository {
	
	private List<PeopleFirstName> peopleFirstNames;
	
	@Value("${data.file.people_first_name}")
	private String fileName;
	
	private void loadPeopleFirstNames() {
		if (peopleFirstNames != null) return;
		peopleFirstNames = new CsvToClass<PeopleFirstName>(fileName, ",") {
			@Override
			protected PeopleFirstName convert(HashMap<String, String> hash) {				
				Gender gender = MyUtil.getEnumFromString(Gender.class, hash.get("gender"));
				return PeopleFirstName
						.builder()
						.firstName(hash.get("first_name"))
						.gender(gender)
						.build();
			}
		}.loadCsv();
	}
		
	public List<PeopleFirstName> randomSearch(Integer count){
		loadPeopleFirstNames();
		return RandomUtil.getRandomList(peopleFirstNames, count);		
	};
	

	public List<PeopleFirstName> randomSearchGender(Integer count, String gender){
		loadPeopleFirstNames();		
		return RandomUtil.getRandomList(peopleFirstNames.stream()
				.filter(p -> p.gender.toString().toLowerCase().equals(gender.toLowerCase()))
				.collect(Collectors.toList()),count);
	}

	public List<PeopleFirstName> findAll() {
		loadPeopleFirstNames();
		return peopleFirstNames;
	};
	
}
