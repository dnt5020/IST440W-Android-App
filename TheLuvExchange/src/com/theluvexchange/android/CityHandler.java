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
	private boolean inAirport = false;
	private boolean inIsActive = false;
	private boolean inIsBase = false;
	private boolean inFormId = false;
	private boolean inCityAblumId = false;
	private boolean inIphoneAblumId = false;
	private boolean inLat = false;
	private boolean inLongitude = false;
	private boolean inCreated = false;
	private boolean inModified = false;

	
	

	
	
	
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
		else if (localName.equals("airport")) {
			// Track if we're inside the state tag
			inAirport = true;
		}
		else if (localName.equals("is_active")) {
			// Track if we're inside the state tag
			inIsActive = true;
		}
		else if (localName.equals("is_base")) {
			// Track if we're inside the state tag
			inIsBase = true;
		}
		else if (localName.equals("form_id")) {
			// Track if we're inside the state tag
			inFormId = true;
		}
		else if (localName.equals("is_base")) {
			// Track if we're inside the state tag
			inIsBase = true;
		}
		else if (localName.equals("city_ablum_id")) {
			// Track if we're inside the state tag
			inCityAblumId = true;
		}
		else if (localName.equals("iphone_ablum_id")) {
			// Track if we're inside the state tag
			inIphoneAblumId = true;
		}
		else if (localName.equals("lat")) {
			// Track if we're inside the state tag
			inLat = true;
		}
		else if (localName.equals("long")) {
			// Track if we're inside the state tag
			inLongitude = true;
		}
		else if (localName.equals("created")) {
			// Track if we're inside the state tag
			inCreated = true;
		}
		else if (localName.equals("modified")) {
			// Track if we're inside the state tag
			inModified = true;
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
		
		else if (inAirport) {
			// If this text is inside a state tag, set the city state
			city.setAirport(new String(ch, start, length));
		}
		
		else if (inIsActive) {
			// If this text is inside a state tag, set the city state
			city.setIsActive(new String(ch, start, length));
		}
		else if (inIsBase) {
			// If this text is inside a state tag, set the city state
			city.setIsBase(new String(ch, start, length));
		}
		
		else if (inFormId) {
			// If this text is inside a state tag, set the city state
			city.setFormId(new String(ch, start, length));
		}
		else if (inCityAblumId) {
			// If this text is inside a state tag, set the city state
			city.setCityAblumId(new String(ch, start, length));
		}
		
		else if (inIphoneAblumId) {
			// If this text is inside a state tag, set the city state
			city.setIphoneAblumId(new String(ch, start, length));
		}
		else if (inLat) {
			// If this text is inside a state tag, set the city state
			city.setLat(new String(ch, start, length));
		}
		else if (inLongitude) {
			// If this text is inside a state tag, set the city state
			city.setLongitude(new String(ch, start, length));
		}
		else if (inCreated) {
			// If this text is inside a state tag, set the city state
			city.setCreated(new String(ch, start, length));
		}
		
		else if (inModified) {
			// If this text is inside a state tag, set the city state
			city.setModified(new String(ch, start, length));
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
			
			inId = false;
			
		} else if (localName.equals("name")) {
			
			inName = false;
			
		} else if (localName.equals("state")) {
			
			inState = false;
		}
		
		else if (localName.equals("airport")) {
			
			inAirport = false;
		}
		else if (localName.equals("is_active")) {
			
			inIsActive = false;
		}
		else if (localName.equals("is_base")) {
			
			inIsBase = false;
		}
		else if (localName.equals("form_id")) {
			
			inFormId = false;
		}
		else if (localName.equals("is_base")) {
		
			inIsBase = false;
		}
		else if (localName.equals("city_ablum_id")) {
			
			inCityAblumId = false;
		}
		else if (localName.equals("iphone_ablum_id")) {
			
			inIphoneAblumId = false;
		}
		else if (localName.equals("lat")) {
		
			inLat = false;
		}
		else if (localName.equals("long")) {
			
			inLongitude = false;
		}
		else if (localName.equals("created")) {
			
			inCreated = false;
		}
		else if (localName.equals("modified")) {
			
			inModified = false;
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
