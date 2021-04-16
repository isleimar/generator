package com.win.people_firts_name;

import java.io.Serializable;

import com.win.people.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@ToString
public class PeopleFirstName implements Serializable{	
	private static final long serialVersionUID = 1L;
	String firstName;
	Gender gender;

}
