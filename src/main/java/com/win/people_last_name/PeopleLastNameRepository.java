package com.win.people_last_name;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.win.services.RandomUtil;
import com.win.services.ReadCsv;


@Repository
public class PeopleLastNameRepository{
	
	private List<PeopleLastName> peopleLastNames;
	
	@Value("${data.file.people_last_name}")
	private String fileName;
	
	private void loadPeopleLastNames() {
		if (peopleLastNames != null) return;
		peopleLastNames = new ArrayList<PeopleLastName>();		
		ReadCsv rc = new ReadCsv(fileName, ",");		
		try {
			rc.openFile();
			while (rc.nextLine()) {
				String name = rc.getValue(0);
				peopleLastNames.add(PeopleLastName
						.builder()
						.lastName(name)
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
	
	public List<PeopleLastName> randomSearch(Integer count){
		loadPeopleLastNames();
		return RandomUtil.getRandomList(peopleLastNames, count);
	}

	public List<PeopleLastName> findAll() {
		loadPeopleLastNames();
		return peopleLastNames;
	};
	

}
