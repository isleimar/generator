package com.win.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RandomUtil {
	
	private static final String[] SPECIAL_CHARS_USER = {"_","-"};
	private static final String[] SPECIAL_CHARS_PASSWD = {"@", "!", "?", "_", "#"};		
	private static final String[] NUMERICAL_CHARS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private static final Integer MIN_WORD_USER = 4;
	private static final Integer MAX_WORD_USER = 8;	
	private static final Integer MIN_PASSWD = 6;
	private static final Integer MAX_PASSWD = 12;
	
	
	public static Integer getRandomInt(int value) {		
		return ThreadLocalRandom
				.current()
				.nextInt(value);
	}
	
	public static Integer getRandomInt(int origin, int bound) {
		return ThreadLocalRandom
				.current()
				.nextInt(origin, bound);
	}
	
	public static Long getRandomLong(Long origin, Long bound) {
		return ThreadLocalRandom
				.current()
				.nextLong(origin, bound);
	}
	
	public static Double getDoubleRandom(Double origin, Double bound) {
		return ThreadLocalRandom
				.current()
				.nextDouble(origin, bound);
	}
	
	public static <T> T getRandomItemList(List<T> originList) {
		if (originList == null || originList.size() == 0) return null;
		int size = originList.size();
		return originList.get(getRandomInt(size));
					
	}
	
	public static <T> List<T> getRandomList(List<T> originList, int count){
		List<T> returnList = new ArrayList<>();
		if (originList == null || count < 1 || originList.size() < 1) return returnList;
		while (returnList.size() < count) {
			returnList.add(getRandomItemList(originList));			
		}
		return returnList;
	}
	
	public static LocalDate getRandomLocalDate(LocalDate startInclusive, LocalDate endExcluisve) {
		long startEpochDay = startInclusive.toEpochDay();
		long endEpochDay = endExcluisve.toEpochDay();		
		long randomDay = getRandomLong(startEpochDay, endEpochDay);
		return LocalDate.ofEpochDay(randomDay);
	}
	
	public static List<LocalDate> getListRandomLocalDate(Integer count, LocalDate startInclusive, LocalDate endExcluisve){
		List<LocalDate> localDates = new ArrayList<LocalDate>();
		for(int i= 0; i < count; i++) {
			localDates.add(getRandomLocalDate(startInclusive, endExcluisve));
		}
		return localDates;		
	}
	
	public static String getUserName(List<String> originWords) {		
		List<String> words = new ArrayList<String>();
		words.addAll(List.of(SPECIAL_CHARS_USER));
		originWords.forEach(w -> words.add(w.toLowerCase())); //LowerCase
		words.addAll(originWords.stream()
				.filter(w -> (w.length() > 4))
				.map(w -> w.toLowerCase().substring(0, 1))
				.collect(Collectors.toList()));
		return getRandomList(words, getRandomInt(MIN_WORD_USER, MAX_WORD_USER)).stream().collect(Collectors.joining(""));
	}
	
	public static String getPassword() {
		List<String> passwd = new ArrayList<String>();
		passwd.addAll(List.of(SPECIAL_CHARS_PASSWD));
		passwd.addAll(List.of(NUMERICAL_CHARS));
		for (int i=0; i<26; i++) {
			passwd.add(String.valueOf((char)(65 + i)));
			passwd.add(String.valueOf((char)(90 + i)));
		}
		return getRandomList(passwd, getRandomInt(MIN_PASSWD, MAX_PASSWD)).stream().collect(Collectors.joining());
	}
	
	
	

}
