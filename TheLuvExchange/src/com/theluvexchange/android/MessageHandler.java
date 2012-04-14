package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MessageHandler extends DefaultHandler {
	// Boolean flags to mark when we are inside of an element
	private boolean inId = false;
	private boolean inBody = false;
	private boolean inCreated = false;
	private boolean inUsername = false;

	// messageData holds the list of Message objects to populate
	private List<Message> messageData;

	// message is used for creating Message objects and setting fields
	private Message message;

	/**
	 * startDocument is called automatically when the parsing begins.
	 */
	@Override
	public void startDocument() throws SAXException {
		messageData = new ArrayList<Message>();
	}

	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equals("city_message")) {
			message = new Message();
			messageData.add(message);
		} else if (localName.equals("id")) {
			// Track if we're inside the id tag
			inId = true;
		} else if (localName.equals("body")) {
			inBody = true;
		} else if (localName.equals("created")) {
			inCreated = true;
		} else if (localName.equals("username")) {
			inUsername = true;
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
			message.setId(new String(ch, start, length));
		} else if (inBody) {
			message.setBody(new String(ch, start, length));
		} else if (inCreated) {
			message.setCreated(new String(ch, start, length));
		} else if (inUsername) {
			message.setUsername(new String(ch, start, length));
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
			// Track if we're inside the id tag
			inId = false;
		} else if (localName.equals("body")) {
			inBody = false;
		} else if (localName.equals("created")) {
			inCreated = false;
		} else if (localName.equals("username")) {
			inUsername = false;
		}
	}

	/**
	 * This method returns the populated list of City objects from the parsing.
	 * @return
	 */
	public List<Message> getMessageData() {
		return messageData;
	}
}
