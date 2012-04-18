package com.theluvexchange.android;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author DeNario Thompson
 * 
 * This class is used by the SAX XMLReader to handle parsing for the list of cities
 * from the web service.
 */
public class BuySellRentHandler extends DefaultHandler {
	// Boolean flags to mark when we are inside of an element
	private boolean inId = false;
	private boolean inUserId = false;
	private boolean inName = false;
	private boolean inBody = false;
	private boolean inPrice = false;
	private boolean inCategoryId = false;
	private boolean inCityId = false;
	private boolean inCreated = false;
	private boolean inAlbumId = false;
	private boolean inFilename = false;
	

	// buySellRentData holds the list of BSR objects to populate
	private List<BuySellRent> buySellRentData;
	
	// buySellRent is used for creating BuySellRent objects and setting fields
	private BuySellRent buySellRent;
	
	/**
	 * startDocument is called automatically when the parsing begins.
	 * Here, we instantiate the list of BuySellRent objects.
	 */
	@Override
	public void startDocument() throws SAXException {
		buySellRentData = new ArrayList<BuySellRent>();
	}
	
	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (localName.equals("asset")) {
			// If it's a new buySellRent, create a new buySellRent object and add it to the list
			buySellRent = new BuySellRent();
			buySellRentData.add(buySellRent);
			
		} else if (localName.equals("id")) {
			// Track if we're inside the id tag
			inId = true;
		} else if (localName.equals("user_id")) {
			// Track if we're inside the user id tag
			inUserId = true;
		} else if (localName.equals("name")) {
			// Track if we're inside the name tag
			inName = true;
		}
		else if (localName.equals("body")) {
			// Track if we're inside the body tag
			inBody = true;
		}
		else if (localName.equals("price")) {
			// Track if we're inside the price tag
			inPrice = true;
		}
		else if (localName.equals("category_id")) {
			// Track if we're inside the category id tag
			inCategoryId = true;
		}
		else if (localName.equals("city_id")) {
			// Track if we're inside the city id tag
			inCityId = true;
		}
		else if (localName.equals("created")) {
			// Track if we're inside the created tag
			inCreated = true;
		}
		else if (localName.equals("album_id")) {
			// Track if we're inside the album id tag
			inAlbumId = true;
		}
		 else if (localName.equals("filename")) {
				// Track if we're inside the id tag
				inFilename = true;
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
			// If this text is inside an id tag, set the buySellRent id
			buySellRent.setId(new String(ch, start, length));
			
		} else if (inUserId) {
			// If this text is inside a userId tag, set the buySellRent user id
			buySellRent.setUserId(new String(ch, start, length));
			
		} else if (inName) {
			// If this text is inside a name tag, set the buySellRent name
			buySellRent.setName(new String(ch, start, length));
		}
		
		else if (inBody) {
			// If this text is inside a body tag, set the buySellRent body
			buySellRent.setBody(new String(ch, start, length));
		}
		
		else if (inPrice) {
			// If this text is inside a price tag, set the buySellRent price
			buySellRent.setPrice(new String(ch, start, length));
		}
		else if (inCategoryId) {
			// If this text is inside a category id tag, set the buySellRent category id
			buySellRent.setCategoryId(new String(ch, start, length));
		}
		
		else if (inCityId) {
			// If this text is inside a city id tag, set the buySellRent city id 
			buySellRent.setCityId(new String(ch, start, length));
		}
		else if (inCreated) {
			// If this text is inside a created tag, set the buySellRent created
			buySellRent.setCreated(new String(ch, start, length));
		}
		
		else if (inAlbumId) {
			// If this text is inside a album id tag, set the buySellRent album id
			buySellRent.setAlbumId(new String(ch, start, length));
		}
		else if (inFilename) {
			// If this text is inside a filename id tag, set the buySellRent album id
			buySellRent.setFilename(new String(ch, start, length));
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
			
		}  else if (localName.equals("user_id")) {
			// Track if we're inside the user_id tag
			inUserId = false;
		} else if (localName.equals("name")) {
			// Track if we're inside the name tag
			inName = false;
		}
		else if (localName.equals("body")) {
			// Track if we're inside the body tag
			inBody = false;
		}
		else if (localName.equals("price")) {
			// Track if we're inside the price tag
			inPrice = false;
		}
		else if (localName.equals("category_id")) {
			// Track if we're inside the category_id tag
			inCategoryId = false;
		}
		else if (localName.equals("city_id")) {
			// Track if we're inside the city_id tag
			inCityId = false;
		}
		else if (localName.equals("created")) {
			// Track if we're inside the created tag
			inCreated = false;
		}
		else if (localName.equals("album_id")) {
			// Track if we're inside the album tag
			inAlbumId = false;
		}
		 else if (localName.equals("filename")) {
				// Track if we're inside the id tag
				inFilename = false;
			}
	}
	
	/**
	 * This method returns the populated list of buySellRent objects from the parsing.
	 * @return
	 */
	public List<BuySellRent> getBSRData() {
		return buySellRentData;
	}
}
