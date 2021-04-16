package com.win;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.people_firts_name.PeopleFirstNameService;
import com.win.people_last_name.PeopleLastNameService;

@Service
public class PopulateTable {
	
	@Autowired
	private PeopleFirstNameService peopleFirstNameService;
	@Autowired
	private PeopleLastNameService peopleLastNameService;
		
	public PopulateTable() {}
	
	public void start() {
		String curDir = System.getProperty("user.dir");
		String path = curDir + "/data/";
		peopleFirstNameService.createForCsv(path + "people_first_name.csv");
		peopleLastNameService.createForCsv(path + "people_last_name.csv");				
	}
}
