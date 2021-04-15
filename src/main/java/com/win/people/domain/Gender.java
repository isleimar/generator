package com.win.people.domain;

public enum Gender {
	M("MALE"),
	F("FEMALE");
	private final String name;
	private Gender(String name) {this.name = name;}
	public String getName() {return this.name;}

}
