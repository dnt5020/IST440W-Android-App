package com.theluvexchange.android;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class PhotosHandler extends DefaultHandler {
	
		// Boolean flags to mark when we are inside of an element
		private boolean inId = false;
		private boolean inFilename = false;
		private boolean inCaption = false;
		private boolean inRatingAverage = false;
		private boolean inRatingTotal = false;
		private boolean inRatingCount = false;
		private boolean inViewerRating = false;

		private List<AlbumPhoto> photosData;
		private AlbumPhoto photo;
		
		/**
		 * startDocument is called automatically when the parsing begins.
		 */
		@Override
		public void startDocument() throws SAXException {
			photosData = new ArrayList<AlbumPhoto>();
		}
		
		/**
		 * startElement is called every time we come across a starting element tag.
		 * Here, we check for the tag name and act accordingly.
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			
			if (localName.equals("photo")) {
				photo = new AlbumPhoto();
				photosData.add(photo);
			} else if (localName.equals("id")) {
				// Track if we're inside the id tag
				inId = true;
			} else if (localName.equals("filename")) {
				inFilename = true;
			} else if (localName.equals("caption")) {
				inCaption = true;
			} else if (localName.equals("rating_avg")) {
				inRatingAverage = true;
			} else if (localName.equals("rating_total")) {
				inRatingTotal = true;
			} else if (localName.equals("rating_count")) {
				inRatingCount = true;
			} else if (localName.equals("viewer_rating")) {
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
				photo.setId(new String(ch, start, length));
			} else if (inFilename) {
				String filename = new String(ch, start, length).trim();
				photo.setFilename(filename);
				try {
					URL url = new URL("http://www.theluvexchange.com/files/photos/75x50/" + filename);
					InputStream inputStream = (InputStream) url.getContent();
					photo.setThumbnail(Drawable.createFromStream(inputStream, "thumbnail"));
				} catch (Exception e) {
					Log.e("TheLuvExchange", "WebServiceError", e);
				}
			} else if (inCaption) {
				photo.setCaption(new String(ch, start, length));
			} else if (inRatingAverage) {
				photo.setRatingAverage(new String(ch, start, length));
			}else if (inRatingTotal) {
				photo.setRatingTotal(new String(ch, start, length));
			} else if (inRatingCount) {
				photo.setRatingCount(new String(ch, start, length));
			} else if (inViewerRating) {
				photo.setViewerRating(new String(ch, start, length));
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
			} else if (localName.equals("filename")) {
				inFilename = false;
			} else if (localName.equals("caption")) {
				inCaption = false;
			} else if (localName.equals("rating_avg")) {
				inRatingAverage = false;
			} else if (localName.equals("rating_total")) {
				inRatingTotal = false;
			} else if (localName.equals("rating_count")) {
				inRatingCount = false;
			} else if (localName.equals("viewer_rating")) {
				inViewerRating = false;
			}
		}

	public List<AlbumPhoto> getRatingData() {
		return photosData;
	}
	
}
