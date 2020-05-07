package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CSVObject {
private JSONArray response;
private String file;
public CSVObject(JSONArray response, String file) {
	super();
	this.response = response;
	this.file = file;
}
public JSONArray getResponse() {
	return response;
}
public void setResponse(JSONArray response) {
	this.response = response;
}
public String getFile() {
	return file;
}
public void setFile(String file) {
	this.file = file;
}
@Override
public String toString() {
	return "CSVObject [response=" + response + ", file=" + file + "]";
}


}
