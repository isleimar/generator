package com.win.people.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.people.domain.People;
import com.win.people.domain.SimplePeople;
import com.win.user.domain.User;
import com.win.user.service.UserService;

@Service
public class PeopleService {
	
	@Autowired
	SimplePeopleService simplePeopleService;
	@Autowired
	UserService userService;
	
	public List<People> getPeoples(Integer count, String gender){
		List<SimplePeople> simplePeoples = simplePeopleService.getListSimplePeoples(count, gender);
		
		return simplePeoples.stream()
				.map(p -> simpleToPeople(p)).collect(Collectors.toList());
	}
	
	private People simpleToPeople(SimplePeople simplePeople) {
		User user = userService.createUser(simplePeople);
		return People.builder()
				.username(user.getUserName())
				.password(user.getPassword())				
				.name(simplePeople.getName())
				.birthdate(simplePeople.getBirthDate())
				.gender(simplePeople.getGender())				
				.build();
	}
}
