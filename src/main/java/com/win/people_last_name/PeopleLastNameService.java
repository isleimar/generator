package com.win.people_last_name;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.people_firts_name.PeopleFirstNameService;
import com.win.services.ReadCsv;

@Service
public class PeopleLastNameService {
	
	@Autowired
	private PeopleLastNameRepository peopleLastNameRepository;
	
	private final Logger LOGGER  = Logger.getLogger(PeopleFirstNameService.class.getName());
	
	public PeopleLastName add(PeopleLastName peopleLastName) {
		PeopleLastName p = peopleLastNameRepository.save(peopleLastName); 
		LOGGER.info("Insert: " + p.toString());		
		return p;
	}
	
	public List<PeopleLastName> listAll(){
		return StreamSupport
				.stream(peopleLastNameRepository.findAll().spliterator(),false)
				.collect(Collectors.toList());
	}
	
	public void createForCsv(String fileName) {
		ReadCsv readCsv = new ReadCsv(fileName, ",");
		try {
			readCsv.openFile();
			while (readCsv.nextLine()) {
				String lastName = readCsv.getValue("last_name");				
				PeopleLastName peopleLastName = new PeopleLastName(null,lastName);				
				add(peopleLastName);
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
	
	public List<PeopleLastName> randomSearch(Integer count){
		return peopleLastNameRepository.randomSearch(count);
	}

}
