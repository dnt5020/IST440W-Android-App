package com.theluvexchange.android;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PostHandler extends DefaultHandler {
	// Boolean flags to mark when we are inside of an element
	private boolean inSuccess = false;
	private boolean inMessage = false;

	private boolean success = false;
	private String message;

	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equals("success")) {
			inSuccess = true;
		} else if (localName.equals("message")) {
			inMessage = true;
		}
	}

	/**
	 * characters is called whenever we come across text between the tags.
	 * It is also useful because it will parse out CDATA which comes in handy here.
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (inSuccess) {
			success = new String(ch, start, length).trim().equals("1");
		} else if (inMessage) {
			message = new String(ch, start, length);
		}
	}

	/**
	 * endElement is called whenever we come across an ending element tag.
	 * Here we check if we're no longer in a tag, and unmark the boolean flag.
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("success")) {
			inSuccess = false;	
		} else if (localName.equals("message")) {
			inMessage = false;	
		}
	}

	/**
	 * This method returns the populated list of City objects from the parsing.
	 * @return
	 */
	public String getResult() {
		return success ? "success" : message;
	}
}
