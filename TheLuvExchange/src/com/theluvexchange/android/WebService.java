package com.theluvexchange.android;

import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

/**
 * @author Tristan Maschke
 * 
 * This class is meant to serve as the base for providing web service calls to
 * the rest of the program.
 * 
 * Implementation as of 3/5/12 includes jsut a static method for getting the list of cities
 */
public class WebService {
	// Base URL is always going to start with this
	private static final String address = "http://www.theluvexchange.com/iphone/";
	
	/**
	 * Static method to provide back a list of City objects received from the Web service
	 * @return
	 */
	public static List<City> getCities() {
		
		// List of cities to be populated and returned
		List<City> cities = null;
		
		try {
			// URL object used to create a connection to the cities XML page
			URL url = new URL(address + "cities");
			
			// SAX XMLReader object used for parsing the XML file
			XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			
			// SAXHandler class used for parsing the XML
			CityHandler handler = new CityHandler();
			
			// Set the XMLReader to use the SAXHandler for parsing rules
			reader.setContentHandler(handler);
			
			// Run the parsing
			reader.parse(new InputSource(url.openStream()));
			
			// Receive the list of City objects from the parse
			cities = handler.getCityData();
			
		} catch (Exception e) {
			// Log error to be able to debug using LogCat
			Log.e("TheLuvExchange", "WebServiceError", e);
		}
		
		// Return the list of cities
		return cities;
	}
} 
