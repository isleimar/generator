package com.win;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneratorApplication implements CommandLineRunner{
			
	@Value("${fill:false}")
	private Boolean fill;	
	
	
	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {				
//		if (fill) populateTable.start();		
	}

}
