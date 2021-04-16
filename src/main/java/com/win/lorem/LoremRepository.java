package com.win.lorem;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.win.services.CsvToClass;
import com.win.services.RandomUtil;

@Repository
public class LoremRepository {
	
	private List<String> loremWords;
	
	@Value("${data.file.lorem}")
	private String fileName;
	
	private void loadLorem() {
		if (loremWords != null) return;				
		loremWords = new CsvToClass<String>(fileName, "#") {
			@Override
			protected String convert(HashMap<String, String> hash) {				
				return hash.get("lorem");
			}
		}.loadCsv();
	}
	
	public String getWord(int index) {
		loadLorem();
		if (loremWords.size() == 0) return "";
		if (index > loremWords.size()) index = loremWords.size() -1;
		return loremWords.get(index);
	}
	
	
	public String getRandomWord() {
		loadLorem();
		return RandomUtil.getRandomItemList(loremWords);
	}
	
	
	
	

}
