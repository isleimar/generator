package com.win.people;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class People {
	String username;
	String password;	
	String name;
	LocalDate birthdate;
	Gender gender;	
	
	

}
