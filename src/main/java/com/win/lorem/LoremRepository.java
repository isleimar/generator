package com.win.lorem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.win.services.RandomUtil;
import com.win.services.ReadCsv;

@Service
public class LoremRepository {
	
	private List<String> loremWords;
	
	@Value("${data.file.lorem}")
	private String pathLorem;
	
	private void loadLorem() {
		if (loremWords != null) return;		
		loremWords = new ArrayList<String>();
		ReadCsv rc = new ReadCsv(pathLorem, ",");
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
		loadLorem();
		if (loremWords.size() == 0) return "";
		if (index > loremWords.size()) index = loremWords.size() -1;
		return loremWords.get(index);
	}
	
	
	public String getRandomWord() {
		return RandomUtil.getRandomItemList(loremWords);
	}
	
	
	
	

}
