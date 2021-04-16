package com.win.people_firts_name;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.people.Gender;
import com.win.services.ReadCsv;

@Service
public class PeopleFirstNameService {
	
	@Autowired
	private PeopleFirstNameRepository peopleFirstNameRepository;
	
	private final Logger LOGGER = Logger.getLogger(PeopleFirstNameService.class.getName());
	
	public PeopleFirstName addPeople(PeopleFirstName peopleName) {
		PeopleFirstName p = peopleFirstNameRepository.save(peopleName);  
		LOGGER.info("Insert: " + p.toString());		
		return p;
	}
	
	public void createForCsv(String fileName) {
		ReadCsv readCsv = new ReadCsv(fileName, ",");
		try {
			readCsv.openFile();
			while (readCsv.nextLine()) {
				String sGender = readCsv.getValue("gender");
				Gender gender = null;
				try {
					gender = Gender.valueOf(sGender);
				}catch (Exception e) {
					System.out.println(sGender + " Não é valor de Gender");
				}
				String name = readCsv.getValue("first_name");
				PeopleFirstName peopleName = new PeopleFirstName(null,name,gender);				
				addPeople(peopleName);
			}
		}catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				readCsv.closeFile();
			} catch (IOException e) {
				System.out.println(e);
			}
		}		
	}
	
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
