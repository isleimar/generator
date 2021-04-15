package com.win.people.domain;

import java.io.Serializable;
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
public class SimplePeople implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String name;
	LocalDate birthDate;
	Gender gender;	

}
