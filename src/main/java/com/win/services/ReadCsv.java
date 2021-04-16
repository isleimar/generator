package com.win.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadCsv {
	
	String fileName;
	String separator;
	String[] columns;
	HashMap<String, String> fields;
	BufferedReader br = null;
	
	public ReadCsv(String fileName, String separator){
		super();
		fields = new HashMap<String, String>();
		this.fileName = fileName;
		this.separator = separator;		
	}
	
	public void openFile() throws IOException{		
		this.br = new BufferedReader(new FileReader(fileName));
		columns = readLine();		
	}
	
	public void closeFile() throws IOException {
		br.close();
	}
	
	private String[] readLine() throws IOException{
		String text = br.readLine();
		if (text != null) {
			return text.split(separator);
		}
		return null;		
	}
	
	public Boolean nextLine() throws IOException {
		String[] line = readLine();
		fields.clear();
		if (line == null) return false;		
		for (int i=0; i<columns.length; i++) {
			String key = columns[i];
			String value = (line.length > i)? line[i] : "";
			fields.put(key, value);						
		}
		return true;
	}
	
	public String getValue(String field) {
		return fields.get(field);
	}	
	
	public String getValue(Integer index) {
		return fields.get(columns[index]);
	}
	
	public String[] getColunms() {
		return this.columns;
	}
	
	public HashMap<String, String> getFiels(){
		return fields;
	}

}
