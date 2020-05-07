package com;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVWriterImpl {
private List<CSVObject> toWrite;
private String[] fieldnames;

public CSVWriterImpl(List<CSVObject> data, String[] fieldnames) {
	super();
	this.toWrite = data;
	this.fieldnames = fieldnames;
}



public void write() {
	try {
		File file = new File(this.toWrite.get(0).getFile());
		FileWriter out = new FileWriter(file);
		CSVWriter writer = new CSVWriter(out);
		
		
		
		writer.writeNext(this.fieldnames);
		
		for(CSVObject cData : this.toWrite) {
			String jsonData = cData.getResponse().toJSONString();
			String cleaned = jsonData.substring(1, jsonData.length()-1);
			String[] data = cleaned.split(",");
			writer.writeNext(data);
		}
		
		writer.close();
	}catch(Exception e) {
		System.out.println("Something went wrong: " + e.getMessage());
	}
}
}
