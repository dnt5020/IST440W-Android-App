package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CommentHandler extends DefaultHandler{
	private boolean newMessage= false;
	private boolean mesId = false;
	private boolean message = false;
	private boolean timeSent  = false;
	private boolean userName  = false;
	private int serialNumber=0;
	
	
	private List<Comment> commentData;
	private Comment comment;
	
	/**
	 * startDocument is called automatically when the parsing begins.
	 */
	@Override
	public void startDocument() throws SAXException {
		commentData = new ArrayList<Comment>();
	}

	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equals("city_message")) {

			comment = new Comment();
			serialNumber++;
			comment.setSerialNumber(serialNumber);
			commentData.add(comment);
			newMessage=true;
		} else if (localName.equals("body")) {
			// Track if we're inside the name tag
			message= true;
		} else if (localName.equals("created")) {
			timeSent = true;
		} else if (localName.equals("username")) {
			userName = true;
		}
	}
	/**
	 * characters is called whenever we come across text between the tags.
	 * It is also useful because it will parse out CDATA which comes in handy here.
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (message) {
			comment.setMessage(new String(ch, start, length));
		} else if (timeSent) {
			comment.setTime(new String(ch, start, length));
		} else if (userName) {
			comment.setUsername(new String(ch, start, length));
		}

	}
	/**
	 * endElement is called whenever we come across an ending element tag.
	 * Here we check if we're no longer in a tag, and unmark the boolean flag.
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(newMessage)
		{
			newMessage=false;
		}else if (message) {
			message=false;		
		} else if (timeSent) {
			timeSent=false;
		} else if (userName) {
			userName=false;
		}
	}
	public List<Comment> getCommentData() {
		return commentData;
	}
}