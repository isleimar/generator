package com.win.user.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.win.people.domain.SimplePeople;
import com.win.service.RandomUtil;
import com.win.user.domain.User;

@Service
public class UserService {

	public User createUser(SimplePeople simplePeople) {
		return User.builder()
				.userName(getUserNames(simplePeople))
				.password(RandomUtil.getPassword())
				.build();
	}
	
	private String getUserNames(SimplePeople simplePeople){
		String words = simplePeople.getName();
		words = words + simplePeople.getBirthDate().format(DateTimeFormatter.ofPattern(" dd MM YYYY"));				
		return RandomUtil.getUserName(List.of(words.split(" ")));
	}

}
