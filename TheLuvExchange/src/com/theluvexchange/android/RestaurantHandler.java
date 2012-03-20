package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RestaurantHandler extends DefaultHandler {
	// Boolean flags to mark when we are inside of an element
	private boolean inId = false;
	private boolean inName = false;
	private boolean inCategory = false;
	private boolean inAddress  = false;
	private boolean inPhone = false;
	private boolean inBody = false;
	private boolean inLatitude = false;
	private boolean inLongitude = false;
	private boolean inDiscounts = false;
	private boolean inRatingTotal = false;
	private boolean inRatingCount = false;
	private boolean inRatingAverage = false;
	private boolean inLocation = false;
	private boolean inViewerRating = false;
	
	// restaurantData holds the list of City objects to populate
	private List<Restaurant> restaurantData;
	
	// city is used for creating City objects and setting fields
	private Restaurant restaurant;
	
	/**
	 * startDocument is called automatically when the parsing begins.
	 * Here, we instantiate the list of City objects.
	 */
	@Override
	public void startDocument() throws SAXException {
		restaurantData = new ArrayList<Restaurant>();
	}
	
	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (localName.equals("restaurant")) {
			
			restaurant = new Restaurant();
			restaurantData.add(restaurant);
			
		} else if (localName.equals("id")) {
			// Track if we're inside the id tag
			inId = true;
		} else if (localName.equals("name")) {
			// Track if we're inside the name tag
			inName = true;
		} else if (localName.equals("category")) {
			// Track if we're inside the state tag
			inCategory = true;
		}
		else if (localName.equals("address")) {
			// Track if we're inside the state tag
			inAddress = true;
		}
		
		else if (localName.equals("phone")) {
			// Track if we're inside the state tag
			inPhone = true;
		}
		else if (localName.equals("body")) {
			// Track if we're inside the state tag
			inBody = true;
		}
		
		else if (localName.equals("latitude")) {
			// Track if we're inside the state tag
			inLatitude = true;
		}
		else if (localName.equals("longitude")) {
			// Track if we're inside the state tag
			inLongitude = true;
		}
		
		else if (localName.equals("discounts")) {
			// Track if we're inside the state tag
			inDiscounts = true;
		}
		
		else if (localName.equals("ratingTotal")) {
			// Track if we're inside the state tag
			inRatingTotal = true;
		}
		
		else if (localName.equals("ratingCount")) {
			// Track if we're inside the state tag
			inRatingCount = true;
		}
		
		else if (localName.equals("ratingAverage")) {
			// Track if we're inside the state tag
			inRatingAverage = true;
		}
		
		else if (localName.equals("location")) {
			// Track if we're inside the state tag
			inLocation = true;
		}
		
		else if (localName.equals("viewerRating")) {
			// Track if we're inside the state tag
			inViewerRating = true;
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
			restaurant.setId(new String(ch, start, length));
			
		} else if (inName) {
			// If this text is inside a name tag, set the city name
			restaurant.setName(new String(ch, start, length));
			
		} else if (inCategory) {
			// If this text is inside a state tag, set the city state
			restaurant.setCategory (new String(ch, start, length));
		}
		 else if (inAddress) {
				// If this text is inside a state tag, set the city state
				restaurant.setAddress (new String(ch, start, length));
			}
		 else if (inPhone) {
				// If this text is inside a state tag, set the city state
				restaurant.setPhone (new String(ch, start, length));
			}
		
		 else if (inBody) {
				// If this text is inside a state tag, set the city state
				restaurant.setBody (new String(ch, start, length));
			}
		
		 else if (inLatitude) {
				// If this text is inside a state tag, set the city state
				restaurant.setLatitude (new String(ch, start, length));
			}
		
		 else if (inLongitude) {
				// If this text is inside a state tag, set the city state
				restaurant.setLongitude (new String(ch, start, length));
			}
		
		 else if (inDiscounts) {
				// If this text is inside a state tag, set the city state
				restaurant.setDiscounts (new String(ch, start, length));
			}
		
		 else if (inRatingTotal) {
				// If this text is inside a state tag, set the city state
				restaurant.setRatingTotal (new String(ch, start, length));
			}
			
			else if (inRatingCount) {
				// If this text is inside a state tag, set the city state
				restaurant.setRatingCount (new String(ch, start, length));
			}
			
			else if (inRatingAverage) {
				// If this text is inside a state tag, set the city state
				restaurant.setRatingAverage (new String(ch, start, length));
			}
			
			else if (inLocation) {
				// If this text is inside a state tag, set the city state
				restaurant.setLocation (new String(ch, start, length));
			}
			else if (inViewerRating) {
				// If this text is inside a state tag, set the city state
				restaurant.setViewerRating (new String(ch, start, length));
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
			
		} else if (localName.equals("category")) {
			// Track if we've exited the state tag
			inCategory = false;
		}
		
	
		else if (localName.equals("address")) {
			inAddress = false;
		}
		else if (localName.equals("phone")) {
			// Track if we're inside the state tag
			inPhone = false;
		}
		else if (localName.equals("body")) {
			// Track if we're inside the state tag
			inBody = false;
		}
		
		else if (localName.equals("latitude")) {
			// Track if we're inside the state tag
			inLatitude = false;
		}
		else if (localName.equals("longitude")) {
			// Track if we're inside the state tag
			inLongitude = false;
		}
		
		else if (localName.equals("discounts")) {
			// Track if we're inside the state tag
			inDiscounts = false;
		}
		
		else if (localName.equals("ratingTotal")) {
			// Track if we're inside the state tag
			inRatingTotal = false;
		}
		
		else if (localName.equals("ratingCount")) {
			// Track if we're inside the state tag
			inRatingCount = false;
		}
		
		else if (localName.equals("ratingAverage")) {
			// Track if we're inside the state tag
			inRatingAverage = false;
		}
		
		else if (localName.equals("location")) {
			// Track if we're inside the state tag
			inLocation = false;
		}
		
		else if (localName.equals("viewerRating")) {
			// Track if we're inside the state tag
			inViewerRating = false;
		}
		
		
	}
	
	/**
	 * This method returns the populated list of City objects from the parsing.
	 * @return
	 */
	public List<Restaurant> getRestaurantData() {
		return restaurantData;
	}
}
