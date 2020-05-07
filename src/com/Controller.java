package com;

import java.net.MalformedURLException;

public class Controller {
	public static void main(String[] args) throws MalformedURLException {
		String sixtyURL = "https://ssd-api.jpl.nasa.gov/cad.api?neo=true&date-min=now&dist-max=0.05";
		String twenty = "https://ssd-api.jpl.nasa.gov/cad.api?neo=true&date-min=now&dist-max=0.05&date-max=2020-12-31";
		String magnitude = "https://ssd-api.jpl.nasa.gov/cad.api?neo=true&date-min=now&dist-max=0.05&date-max=2020-12-31&sort=h";
		String names = "https://ssd-api.jpl.nasa.gov/cad.api?fullname=true&neo=true&date-min=now&dist-max=0.05&date-max=2020-12-31&sort=object";
		
		JPLRequester sixtyR = new JPLRequester(sixtyURL, "./Sixty.csv");
		JPLRequester twentyR = new JPLRequester(twenty, "./2020.csv");
		JPLRequester magnitudeR = new JPLRequester(magnitude, "./Magnitude.csv");
		JPLRequester namesR = new JPLRequester(names, "./Names.csv");
		
		sixtyR.connect_and_print();
		twentyR.connect_and_print();
		magnitudeR.connect_and_print();
		namesR.connect_and_print();
	}
}
