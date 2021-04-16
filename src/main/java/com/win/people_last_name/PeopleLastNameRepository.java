package com.win.people_last_name;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.win.services.CsvToClass;
import com.win.services.RandomUtil;

@Repository
public class PeopleLastNameRepository{
	
	private List<PeopleLastName> peopleLastNames;
	
	@Value("${data.file.people_last_name}")
	private String fileName;
	
	private void loadPeopleLastNames() {
		if (peopleLastNames != null) return;
		peopleLastNames = new CsvToClass<PeopleLastName>(fileName, ",") {
			@Override
			protected PeopleLastName convert(HashMap<String, String> hash) {
				return PeopleLastName
						.builder()
						.lastName(hash.get("last_name"))
						.build();
			}
		}.loadCsv();
	}
	
	public List<PeopleLastName> randomSearch(Integer count){
		loadPeopleLastNames();
		return RandomUtil.getRandomList(peopleLastNames, count);
	}

	public List<PeopleLastName> findAll() {
		loadPeopleLastNames();
		return peopleLastNames;
	};
	

}
