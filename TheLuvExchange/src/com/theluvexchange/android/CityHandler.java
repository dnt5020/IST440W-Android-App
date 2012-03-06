package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Tristan Maschke
 * 
 * This class is used by the SAX XMLReader to handle parsing for the list of cities
 * from the web service.
 */
public class CityHandler extends DefaultHandler {
	// Boolean flags to mark when we are inside of an element
	private boolean inId = false;
	private boolean inName = false;
	private boolean inState = false;
	
	// cityData holds the list of City objects to populate
	private List<City> cityData;
	
	// city is used for creating City objects and setting fields
	private City city;
	
	/**
	 * startDocument is called automatically when the parsing begins.
	 * Here, we instantiate the list of City objects.
	 */
	@Override
	public void startDocument() throws SAXException {
		cityData = new ArrayList<City>();
	}
	
	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (localName.equals("city")) {
			// If it's a new city, create a new city object and add it to the list
			city = new City();
			cityData.add(city);
			
		} else if (localName.equals("id")) {
			// Track if we're inside the id tag
			inId = true;
		} else if (localName.equals("name")) {
			// Track if we're inside the name tag
			inName = true;
		} else if (localName.equals("state")) {
			// Track if we're inside the state tag
			inState = true;
		}
	}
	
	/**
	 * characters is called whenever we come across text between the tags.
	 * It is also useful because it will parse out CDATA which comes in handy here.
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if (inId) {
			// If this text is inside an id tag, set the city id
			city.setId(new String(ch, start, length));
			
		} else if (inName) {
			// If this text is inside a name tag, set the city name
			city.setName(new String(ch, start, length));
			
		} else if (inState) {
			// If this text is inside a state tag, set the city state
			city.setState(new String(ch, start, length));
		}
	}
	
	/**
	 * endElement is called whenever we come across an ending element tag.
	 * Here we check if we're no longer in a tag, and unmark the boolean flag.
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if (localName.equals("id")) {
			// Track if we've exited the id tag
			inId = false;
			
		} else if (localName.equals("name")) {
			// Track if we've exited the name tag
			inName = false;
			
		} else if (localName.equals("state")) {
			// Track if we've exited the state tag
			inState = false;
		}
	}
	
	/**
	 * This method returns the populated list of City objects from the parsing.
	 * @return
	 */
	public List<City> getCityData() {
		return cityData;
	}
}
