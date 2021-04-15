package com.win.people.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.people.domain.PeopleFirstName;
import com.win.people.domain.PeopleLastName;
import com.win.people.domain.SimplePeople;
import com.win.service.RandomUtil;

@Service
public class SimplePeopleService {
	
	private int MINUS_YEARS = 100;
	private int MIN_LAST_NAME = 2;
	private int MAX_LAST_NAME = 4;
	
	@Autowired
	PeopleFirstNameService peopleFirstNameService;
	@Autowired
	PeopleLastNameService peopleLastNameService;
		
	public List<SimplePeople> getListSimplePeoples(Integer count, String gender){
		List<PeopleLastName> peopleLastNames = peopleLastNameService.randomSearch(count * MAX_LAST_NAME);
		return getFirstNames(count, gender)
				.stream()
				.map(p -> getSimplePeople(p, peopleLastNames))
				.collect(Collectors.toList());
	}
	
	private List<PeopleFirstName> getFirstNames(int count, String gender){
		List<PeopleFirstName> peopleFirstNames = peopleFirstNameService.randomSearchGender(count, gender);
		int qtd = peopleFirstNames.size();
		if(qtd == 0 || qtd >= count) return peopleFirstNames;
		peopleFirstNames.addAll(RandomUtil.getRandomList(peopleFirstNames, count - qtd));
		return peopleFirstNames;	
	}
	
	private SimplePeople getSimplePeople(PeopleFirstName peopleFirstName, List<PeopleLastName> peopleLastNames) {
		String lastname = getLastName(peopleLastNames);
		return SimplePeople.builder()
				.name(peopleFirstName.getFirstName() + " " + lastname)
				.birthDate(getLocalDates())
				.gender(peopleFirstName.getGender())
				.build();
	}
	
	private LocalDate getLocalDates(){
		LocalDate startDate = LocalDate.now().minusYears(MINUS_YEARS);
		LocalDate endDate = LocalDate.now();
		return RandomUtil.getRandomLocalDate(startDate, endDate);
	}
	
	private String getLastName(List<PeopleLastName> peopleLastNames) {
		int qtdLastName = RandomUtil.getRandomInt(MIN_LAST_NAME, MAX_LAST_NAME);
		return RandomUtil
				.getRandomList(peopleLastNames, qtdLastName)
				.stream()
				.map(p -> p.getLastName())
				.collect(Collectors.joining(" "));
	}

}
