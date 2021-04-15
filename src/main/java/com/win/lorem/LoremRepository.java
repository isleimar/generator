package com.win.lorem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.win.service.RandomUtil;
import com.win.service.ReadCsv;

@Service
public class LoremRepository {
	
	private List<String> loremWords;
	
	public LoremRepository() {
		String curDir = System.getProperty("user.dir");
		String path = curDir + "/data/";
		loadLorem(path + "lorem_ipsum.csv");		
	}
	
	private void loadLorem(String fileName) {
		loremWords = new ArrayList<String>();
		ReadCsv rc = new ReadCsv(fileName, ",");
		try {
			try {
				rc.openFile();
				while (rc.nextLine()) {
					loremWords.add(rc.getValue(0));
				}
			}catch (Exception e) {
				System.out.println("Falha ao carregar arquivo.");
			}finally {
				rc.closeFile();
			}
		}catch (Exception e) {
			System.out.println("Falha ao carregar arquivo.");
		}
	}
	
	public String getWord(int index) {
		if (loremWords.size() == 0) return "";
		if (index > loremWords.size()) index = loremWords.size() -1;
		return loremWords.get(index);
	}
	
	
	public String getRandomWord() {
		return RandomUtil.getRandomItemList(loremWords);
	}
	
	
	
	

}
