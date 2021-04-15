package com.win.lorem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.win.service.RandomUtil;

@Service
public class LoremService {
	
	@Autowired
	LoremRepository loremRepository;
	
	private static int MIN_COMMA_SPACE = 2;
	private static int MAX_COMMA_SPACE = 14;	
	private static int MIN_PHRASE_SPACE = 10;
	private static int MAX_PHRASE_SPACE = 20;
	private static int MIN_PARAGRAPH_SPACE = 30;
	private static int MAX_PARAGRAPH_SPACE = 150;
	
	private int commaSpace = 0;
	private int phraseSpace = 0;
	private int paragraphSpace = 0;
	
	private Boolean isNextPhrase = true;
	private Boolean isNextParagraph = true;
	
	private int countWord = 0;
	private int countPhrase = 0;
	private int countParagraph = 0;
	
	
	public String getLoremForWords(int count, Boolean hasIntroduction) {
		startAll();		
		String text = "";
		while (countWord < count) {
			text = text + getNextWord(hasIntroduction);
		}
		return text + ".";
	}
	
	public String getLoremForPhrase(int count) {
		startAll();		
		String text = "";
		while (countPhrase < count) {	
			paragraphSpace = MAX_PARAGRAPH_SPACE;
			text = text + getNextWord(false);		
		}
		return text.replace(". ", ".\n");
	}
	
	public String getLoremForParagraph(int count, Boolean hasIntroduction) {
		startAll();		
		String text = "";
		while (countParagraph < count) {			
			text = text + getNextWord(hasIntroduction);
		}
		return text;
	}
	
	private void startAll() {
		countParagraph = 0;
		countPhrase = 0;
		countWord = 0;
		stratParagraph();
	}
	
	private void startComma() {
		commaSpace = RandomUtil.getRandomInt(MIN_COMMA_SPACE, MAX_COMMA_SPACE);
	}
	
	private void startPhrase() {
		startComma();
		isNextPhrase = true;
		phraseSpace = RandomUtil.getRandomInt(MIN_PHRASE_SPACE, MAX_PHRASE_SPACE);		
	}
	
	public void stratParagraph() {
		startPhrase();
		isNextParagraph = true;
		paragraphSpace = RandomUtil.getRandomInt(MIN_PARAGRAPH_SPACE, MAX_PARAGRAPH_SPACE);
	}
	
	private String getNextWord(Boolean hasIntroduction) {
			
		if (isFinalParagraph()) return ".";
		if (isFinalPhrase()) return ".";
		if (isFinalComma()) return  ",";
		
		String word;
				
		if (countWord < 8 && hasIntroduction) {
			word = loremRepository.getWord(countWord);			
		} else {
			word = loremRepository.getRandomWord();
		}
		countWord ++;				
		
		if (isNextPhrase || isNextParagraph) word = StringUtils.capitalize(word);
		if (isNextPhrase && !isNextParagraph) word = " " + word;
		if (isNextParagraph && countWord > 1) word = "\n" + word;
		if (!isNextParagraph && !isNextPhrase) word = " " + word;		
		
		isNextPhrase = false;
		isNextParagraph = false;		
		
		return word;
	}
	
	private Boolean isFinalComma() {
		commaSpace--;
		if (commaSpace < 0) {
			startComma();
			return true && phraseSpace > 1 && paragraphSpace > 1;
		}
		return false;
	}
	
	private Boolean isFinalPhrase() {
		phraseSpace--;
		if (phraseSpace < 0) {
			startPhrase();
			countPhrase++;
			return true && paragraphSpace > 1;
		}
		return false;
	}
	
	private Boolean isFinalParagraph() {
		paragraphSpace--;
		if (paragraphSpace < 0) {
			stratParagraph();
			countParagraph++;
			return true;
		}
		return false;
	}

}
