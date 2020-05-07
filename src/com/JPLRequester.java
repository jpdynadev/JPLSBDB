package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JPLRequester {
private String url;
private String filename;

public JPLRequester(String url, String filename) throws MalformedURLException {
	super();
	this.url = url;
	this.filename = filename;
}
public void connect_and_print(){
	try {
	URL URLforSixtyDays = new URL(this.url);
	HttpURLConnection connection = (HttpURLConnection) URLforSixtyDays.openConnection();
	connection.setRequestMethod("GET");
	connection.setRequestProperty("Content-Type", "application/json");
	
	int response_status = connection.getResponseCode();
	if(response_status == HttpURLConnection.HTTP_OK) {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inLine;
		StringBuffer content = new StringBuffer();
		
		while((inLine = in.readLine()) != null) {
			content.append(inLine);
		}
		String jsonContent = content.toString();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonResponse = (JSONObject) jsonParser.parse(jsonContent);
		JSONArray jsonData = (JSONArray) jsonResponse.get("data");
		String fieldValues=  jsonResponse.get("fields").toString();
		String fieldnameCleaned = fieldValues.substring(1, fieldValues.length()-1);
		String[] fieldnames = fieldnameCleaned.split(",");
		List<CSVObject> data = new ArrayList<>();
		for(int i = 0; i < jsonData.size(); i++) {
			JSONArray tempJSON = (JSONArray) jsonData.get(i);
			CSVObject temp = new CSVObject(tempJSON, this.filename);
			data.add(temp);
		}
		CSVWriterImpl runner = new CSVWriterImpl(data, fieldnames);
		runner.write();
		in.close();
	}
	connection.disconnect();
	}catch(Exception e) {
		
	}
	
}
}
