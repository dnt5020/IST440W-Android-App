package com.theluvexchange.android;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoginHandler extends DefaultHandler {
	// Boolean flags to mark when we are inside of an element
	private boolean inSuccess = false;
	private boolean inUserId = false;
	private boolean inCode = false;
	private boolean inAllowAdd = false;
	private boolean inMessage = false;

	private User user = null;

	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equals("success")) {
			inSuccess = true;
		} else if (localName.equals("user_id")) {
			inUserId = true;
		} else if (localName.equals("code")) {
			inCode = true;
		} else if (localName.equals("allow_add")) {
			inAllowAdd = true;
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
			String success = new String(ch, start, length);
			if (success.equals("1")) {
				user = new User();
			}
		} else if (inUserId && user != null) {
			user.setUserId(new String(ch, start, length));
		} else if (inCode && user != null) {
			user.setCode(new String(ch, start, length));
		} else if (inAllowAdd && user != null) {
			user.setAllowAdd(new String(ch, start, length));
		} else if (inMessage && user != null) {
			user.setMessage(new String(ch, start, length));
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
		} else if (localName.equals("user_id")) {
			inUserId = false;
		} else if (localName.equals("code")) {
			inCode = false;
		} else if (localName.equals("allow_add")) {
			inAllowAdd = false;
		} else if (localName.equals("message")) {
			inMessage = false;
		}
	}

	public User getUser() {
		return user;
	}

}
