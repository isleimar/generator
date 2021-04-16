package com.win.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CsvToClass<T>{
		
	private ReadCsv readCsv;	
	private String fileName;
	private String separator; 	
	protected abstract T convert(HashMap<String, String> hash);
	
	public CsvToClass(String fileName, String separator){
		super();		
		this.fileName = fileName;
		this.separator = separator;
	}
	
	public List<T> loadCsv() {
		List<T> listClass = new ArrayList<T>();
		readCsv = new ReadCsv(fileName, separator);
		try {
			readCsv.openFile();
			while(readCsv.nextLine()) {
				listClass.add(convert(readCsv.getFiels()));				
			}			
		}catch (Exception e) {
			System.out.println(String.format("Falha ao abrir o aquivo:\n \"%s\"", fileName));
		} finally {
			try {
				if (readCsv != null)
					readCsv.closeFile();			
			} catch (Exception e) {
				System.out.println(String.format("Falha ao fechar o aquivo:\n \"%s\"", fileName));
			}
		}
		return listClass;
	}
	

}
