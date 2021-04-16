package com.win.people_firts_name;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.win.people.Gender;
import com.win.services.MyUtil;
import com.win.services.RandomUtil;
import com.win.services.ReadCsv;

@Repository
public class PeopleFirstNameRepository {
	
	private List<PeopleFirstName> peopleFirstNames;
	
	@Value("${data.file.people_first_name}")
	private String fileName;
	
	private void loadPeopleFirstNames() {
		if (peopleFirstNames != null) return;
		peopleFirstNames = new ArrayList<PeopleFirstName>();		
		ReadCsv rc = new ReadCsv(fileName, ",");		
		try {
			rc.openFile();
			while (rc.nextLine()) {
				Gender gender = MyUtil.getEnumFromString(Gender.class, rc.getValue("gender"));
				String name = rc.getValue("first_name");
				peopleFirstNames.add(PeopleFirstName
						.builder()
						.firstName(name)
						.gender(gender)
						.build());
			}
		}catch (Exception e) {
			System.out.println("Falha ao abrir arquivo.");
		} finally {
			try {
				if (rc != null)
					rc.closeFile();
			}catch (Exception e) {
				System.out.println("Falha ao fechar arquivo.");
			} 
		}
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
