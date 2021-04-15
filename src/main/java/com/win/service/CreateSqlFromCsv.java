package com.win.service;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CreateSqlFromCsv {
	
	String csvFile;
	String fileName;
	String fields;
	ReadCsv readCsv;	
	
	public CreateSqlFromCsv(String csvFile, String outFile) {
		this.csvFile = csvFile;
		setFileName();
		readCsv = new ReadCsv(csvFile, ",");
		try (BufferedWriter buffWr = new BufferedWriter(new FileWriter(outFile))) {
			readCsv.openFile();
			setFields(readCsv.getColunms());			
			while (readCsv.nextLine()) {
				String line = getLine(readCsv.columns);
				System.out.println(line);
				buffWr.append(line);
				buffWr.newLine();
			}			
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			try	{readCsv.closeFile();}
			catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private void setFields(String[] colunms) {		 
		fields = "";		
		for (int i = 0; i< colunms.length; i++) {
			fields = fields +
					colunms[i] +
					(i < colunms.length -1?", ":"");
		}
	}
	
	private String getLine(String[] colunms) {		
		String values = "";
		for(int i=0; i<colunms.length; i++) {
			String value = readCsv.getValue(colunms[i]);
			value = value.replace("\'", "\'\'");			
			values = values + value	 + 
					(i < colunms.length -1?"', '":"");
			
		}		
		return String.format("INSERT INTO %s (%s) VALUES ('%s');",this.fileName.substring(0,this.fileName.length() -4),this.fields, values);
	}
	
	private void setFileName() {
		String separator = System.getProperty("file.separator");
		String[] dirs = csvFile.split(separator);
		this.fileName = dirs[dirs.length - 1];
	}
	

}
