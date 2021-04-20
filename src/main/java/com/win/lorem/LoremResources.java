package com.win.lorem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/lorem/")
public class LoremResources {
	
	@Autowired
	LoremService loremService;
	
	@GetMapping("words")
	public ResponseEntity<?> loremWords(@RequestParam(required = false) Integer count, @RequestParam(required = false) Boolean hasIntro){
		if (count == null) count = 100;
		if (hasIntro == null) hasIntro = true;
		return ResponseEntity.ok(loremService.getLoremForWords(count, hasIntro));
	}
	
	@GetMapping("paragraphs")
	public ResponseEntity<?> loremParagraphs(@RequestParam(required = false) Integer count, @RequestParam(required = false) Boolean hasIntro){
		if (count == null) count = 100;
		if (hasIntro == null) hasIntro = true;
		return ResponseEntity.ok(loremService.getLoremForParagraph(count, hasIntro));
	}
	
	@GetMapping("phrases")
	public ResponseEntity<?> loremPhrases(@RequestParam(required = false) Integer count){
		return ResponseEntity.ok(loremService.getLoremForPhrase(count));
	}

	@GetMapping("bytes")
	public ResponseEntity<?> loremBytes(@RequestParam(required = false) Integer count, @RequestParam(required = false) Boolean hasIntro){
		if (count == null) count = 100;
		return ResponseEntity.ok(loremService.getLoremForBytes(count, hasIntro));
	}

}
