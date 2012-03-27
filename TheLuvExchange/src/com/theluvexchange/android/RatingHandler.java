package com.theluvexchange.android;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RatingHandler extends DefaultHandler {
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
	private boolean inCreated = false;
	private boolean inUserName = false;
	private boolean inUserId = false;

	private List<Rating> ratingData;
	private Rating rating;

	/**
	 * startDocument is called automatically when the parsing begins.
	 */
	@Override
	public void startDocument() throws SAXException {
		ratingData = new ArrayList<Rating>();
	}

	/**
	 * startElement is called every time we come across a starting element tag.
	 * Here, we check for the tag name and act accordingly.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equals("ratings")) {

			rating = new Rating();
			ratingData.add(rating);

		} else if (localName.equals("id")) {
			// Track if we're inside the id tag
			inId = true;
		} else if (localName.equals("name")) {
			// Track if we're inside the name tag
			inName = true;
		} else if (localName.equals("category")) {
			inCategory = true;
		} else if (localName.equals("address")) {
			inAddress = true;
		} else if (localName.equals("phone")) {
			inPhone = true;
		} else if (localName.equals("body")) {
			inBody = true;
		} else if (localName.equals("latitude")) {
			inLatitude = true;
		} else if (localName.equals("longitude")) {
			inLongitude = true;
		} else if (localName.equals("discounts")) {
			inDiscounts = true;
		} else if (localName.equals("rating_total")) {
			inRatingTotal = true;
		} else if (localName.equals("rating_count")) {
			inRatingCount = true;
		} else if (localName.equals("rating_avg")) {
			inRatingAverage = true;
		} else if (localName.equals("location")) {
			inLocation = true;
		} else if (localName.equals("viewer_rating")) {
			inViewerRating = true;
		} else if (localName.equals("created")) {
			inCreated = true;
		} else if (localName.equals("username")) {
			inUserName = true;
		} else if (localName.equals("userid")) {
			inUserId = true;
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
			rating.setId(new String(ch, start, length));
		} else if (inName) {
			rating.setName(new String(ch, start, length));
		} else if (inCategory) {
			rating.setCategory(new String(ch, start, length));
		} else if (inAddress) {
			rating.setAddress(new String(ch, start, length));
		} else if (inPhone) {
			rating.setPhone(new String(ch, start, length));
		} else if (inBody) {
			rating.setBody(new String(ch, start, length));
		} else if (inLatitude) {
			rating.setLatitude(new String(ch, start, length));
		} else if (inLongitude) {
			rating.setLongitude(new String(ch, start, length));
		} else if (inDiscounts) {
			rating.setDiscounts(new String(ch, start, length));
		} else if (inRatingTotal) {
			rating.setRatingTotal(new String(ch, start, length));
		} else if (inRatingCount) {
			rating.setRatingCount(new String(ch, start, length));
		} else if (inRatingAverage) {
			rating.setRatingAverage(new String(ch, start, length));
		} else if (inLocation) {
			rating.setLocation(new String(ch, start, length));
		} else if (inViewerRating) {
			rating.setViewerRating(new String(ch, start, length));
		} else if (inCreated) {
			rating.setCreated(new String(ch, start, length));
		} else if (inUserName) {
			rating.setUserName(new String(ch, start, length));
		} else if (inUserId) {
			rating.setUserId(new String(ch, start, length));
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
		} else if (localName.equals("address")) {
			inAddress = false;
		} else if (localName.equals("phone")) {
			inPhone = false;
		} else if (localName.equals("body")) {
			inBody = false;
		} else if (localName.equals("latitude")) {
			inLatitude = false;
		} else if (localName.equals("longitude")) {
			inLongitude = false;
		} else if (localName.equals("discounts")) {
			inDiscounts = false;
		} else if (localName.equals("rating_total")) {
			inRatingTotal = false;
		} else if (localName.equals("rating_count")) {
			inRatingCount = false;
		} else if (localName.equals("rating_avg")) {
			inRatingAverage = false;
		} else if (localName.equals("location")) {
			inLocation = false;
		} else if (localName.equals("viewer_rating")) {
			inViewerRating = false;
		} else if (localName.equals("created")) {
			inCreated = false;
		} else if (localName.equals("username")) {
			inUserName = false;
		} else if (localName.equals("userid")) {
			inUserId = false;
		}
	}

	public List<Rating> getRatingData() {
		return ratingData;
	}
}
